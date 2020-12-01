package huonglh.controllers;

import huonglh.blos.ArticleBLO;
import huonglh.blos.NotificationBLO;
import huonglh.entities.Account;
import huonglh.entities.Article;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau Huong
 */
@WebServlet(name = "GetArticleController", urlPatterns = {"/GetArticleController"})
public class GetArticleController extends HttpServlet {

    private static final String SUCCESS = "main.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            ArticleBLO artBLO = new ArticleBLO();
            NotificationBLO notiBLO = new NotificationBLO();

            HttpSession session = request.getSession();
            int pageSize = 20;
            String pageNumber = (String) request.getParameter("pageNo");
            int pageNo;
            long totalPages;
            if (pageNumber == null) {
                pageNo = 1;
            } else {
                pageNo = Integer.parseInt(pageNumber);
            }
            List articleList = artBLO.getArticleFromTo(pageNo, pageSize);
            long count = artBLO.getCountArticle();

            Account acc = (Account) session.getAttribute("USER");
            List notiList = notiBLO.getAllNotify(acc.getEmail());
            request.setAttribute("LIST", articleList);
            
            if (count % pageSize == 0) {
                totalPages = count / pageSize;
            } else {
                totalPages = (count /pageSize)+1;
            }
            session.setAttribute("TOTALPAGES", totalPages);
            session.setAttribute("NOTI", notiList);
        } catch (Exception e) {
            log("ERROR at ViewArticleController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
