package huonglh.controllers;

import huonglh.blos.ArticleBLO;
import huonglh.entities.Account;
import huonglh.entities.Article;
import huonglh.entities.Status;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Hau Huong
 */
public class PostController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("USER");
            if (!acc.getRole().equals("Admin")) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    log("ERROR at PostController: " + e.getMessage());
                }
                Iterator it = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                String realPath = null;
                ArticleBLO blo = new ArticleBLO();
                long count = blo.getCountArticle() + 1;
                while (it.hasNext()) {
                    FileItem item = (FileItem) it.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            if (itemName.length() > 0) {
                                fileName = "Article" + count + ".JPG";;
                                realPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                                File savedFile = new File(realPath);
                                item.write(savedFile);
                            }
                        } catch (Exception e) {
                            log("ERROR at PostController: " + e.getMessage());
                        }
                    }
                }
                String title = (String) params.get("txtTitle");
                String description = (String) params.get("txtDescription");

                Date currentDate = Calendar.getInstance().getTime();
                Article art = new Article(title, description, fileName, currentDate, acc, new Status(2));

                if (blo.insertArticle(art)) {
                } else {
                    request.setAttribute("ERROR", "Something's happened");
                }
            } else {
                request.setAttribute("FAILED", "Admin can't post article");
            }
        } catch (Exception e) {
            log("ERROR at PostController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("GetArticleController").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
