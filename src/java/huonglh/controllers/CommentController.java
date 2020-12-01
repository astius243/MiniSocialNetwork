package huonglh.controllers;

import huonglh.blos.CommentBLO;
import huonglh.blos.NotificationBLO;
import huonglh.entities.Account;
import huonglh.entities.Article;
import huonglh.entities.Comment;
import huonglh.entities.Notification;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau Huong
 */
public class CommentController extends HttpServlet {

    private static final String SUCCESS = "ViewArticleController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("USER");
            if (!acc.getRole().equals("Admin")) {
                String content = request.getParameter("txtContent");
                Date date = Calendar.getInstance().getTime();
                Article art = (Article) session.getAttribute("ARTICLE");
                Comment cmt = new Comment(content, date, acc, art);
                CommentBLO blo = new CommentBLO();
                if (blo.insertComment(cmt)) {
                    if (!acc.getEmail().equals(art.getEmail().getEmail())) {
                        NotificationBLO notiBLO = new NotificationBLO();
                        String cnt = acc.getName() + " commented " + art.getTitle();
                        Notification noti = new Notification(date, cnt, art);
                        notiBLO.insertNotify(noti);
                    }
                }
            } else {
                request.setAttribute("FAILED", "Admin can't comment on article");
            }
        } catch (Exception e) {
            log("ERROR at CommentController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ViewArticleController").forward(request, response);
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
