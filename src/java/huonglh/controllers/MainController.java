/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Hau Huong
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN = "LoginController";
    private static final String REGISTER = "RegisterController";
    private static final String GET_ARTICLE_LIST = "GetArticleController";
    private static final String VIEW_ACTICLE = "ViewArticleController";
    private static final String POST = "PostController";
    private static final String COMMENT = "CommentController";
    private static final String SEARCH = "SearchController";
    private static final String EMOTION = "EmotionController";
    private static final String BACK = "BackController";
    private static final String LOGOUT = "LogoutController";
    private static final String ERROR = "index.jsp";
    private static final String VERIFICATION = "VerificationController";
    private static final String DELETE_ARTICLE = "DeleteArticleController";
    private static final String DELETE_COMMENT = "DeleteCommentController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                url = POST;
            } else {
                String action = request.getParameter("action");
                switch (action) {
                    case "Login":
                        url = LOGIN;
                        break;
                    case "Register":
                        url = REGISTER;
                        break;
                    case "GetArticle":
                        url = GET_ARTICLE_LIST;
                        break;
                    case "ViewArticle":
                        url = VIEW_ACTICLE;
                        break;
                    case "Comment":
                        url = COMMENT;
                        break;
                    case "Search":
                        url = SEARCH;
                        break;
                    case "Emotion":
                        url = EMOTION;
                        break;
                    case "Verify":
                        url = VERIFICATION;
                        break;
                    case "DeleteArticle":
                        url = DELETE_ARTICLE;
                        break;
                    case "DeleteComment":
                        url = DELETE_COMMENT;
                        break;
                    case "Back":
                        url = BACK;
                        break;
                    case "Logout":
                        url = LOGOUT;
                        break;
                    default:
                        url=ERROR;
                        request.setAttribute("FAILED", "Something's wrong happened");
                        break;
                }
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
