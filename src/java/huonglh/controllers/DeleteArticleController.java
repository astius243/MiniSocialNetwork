package huonglh.controllers;

import huonglh.blos.ArticleBLO;
import huonglh.blos.CommentBLO;
import huonglh.blos.EmotionBLO;
import huonglh.blos.NotificationBLO;
import huonglh.entities.Account;
import huonglh.entities.Article;
import huonglh.entities.Notification;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DeleteArticleController extends HttpServlet {

    private static final String ERROR = "ViewArticleController";
    private static final String SUCCESS = "BackController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Article article = (Article) session.getAttribute("ARTICLE");
            CommentBLO commentBLO = new CommentBLO();
            ArticleBLO acticleBLO = new ArticleBLO();
            EmotionBLO emotionBLO = new EmotionBLO();
            Account acc = (Account) session.getAttribute("USER");
            if (acticleBLO.deleteArticle(article.getArticleID())) {
                url = SUCCESS;
                if (acc.getRole().equals("Admin") && !acc.getEmail().equals(article.getEmail().getEmail())) {
                    Date date = Calendar.getInstance().getTime();
                    Notification noti = new Notification(date, "Admin delelted your article", article);
                    NotificationBLO notiBLO = new NotificationBLO();
                    notiBLO.insertNotify(noti);
                }
            } else {
                request.setAttribute("FAILED", "Something's wrong happened");
            }
        } catch (Exception e) {
            log("ERROR at DeleteArticleController: " + e.getMessage());
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
