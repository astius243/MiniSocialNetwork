/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.controllers;

import huonglh.blos.ArticleBLO;
import huonglh.blos.CommentBLO;
import huonglh.blos.EmotionBLO;
import huonglh.entities.Article;
import huonglh.entities.Emotion;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau Huong
 */
public class ViewArticleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Article artSession = (Article) session.getAttribute("ARTICLE");
            int id;
            if (artSession == null) {
                id = Integer.parseInt(request.getParameter("articleID"));
            } else {
                id = artSession.getArticleID();
            }
            ArticleBLO articleBLO = new ArticleBLO();
            Article art = articleBLO.viewArticle(id);

            CommentBLO commentBLO = new CommentBLO();
            List comment = commentBLO.getAllComments(art);

            EmotionBLO emotionBLO = new EmotionBLO();
            long likes =  emotionBLO.getLikeCount(art);
            long dislikes = emotionBLO.getDislikeCount(art);

            session.setAttribute("ARTICLE", art);
            session.setAttribute("COMMENTS", comment);
            session.setAttribute("LIKES", likes);
            session.setAttribute("DISLIKES", dislikes);

        } catch (Exception e) {
            log("ERROR at ViewArticleController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("article.jsp").forward(request, response);
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
