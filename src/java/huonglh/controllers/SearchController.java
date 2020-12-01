package huonglh.controllers;

import huonglh.blos.ArticleBLO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "main.jsp";
        try {
            HttpSession session = request.getSession();
            String pageNumber = request.getParameter("pageNo");
            String search = request.getParameter("txtSearch");
            if (search.length() > 0) {
                ArticleBLO blo = new ArticleBLO();
                int pageSize = 6;
                int pageNo;
                if (pageNumber == null) {
                    pageNo = 1;
                } else {
                    pageNo = Integer.parseInt(pageNumber);
                }

                long count = blo.getCountArticleByTitle(search);
                List list = blo.searchArticleByTitleFromTo(pageNo, pageSize, search);
                long totalPages;
                if (count % pageSize == 0) {
                    totalPages = count / pageSize;
                } else {
                    totalPages = (count / pageSize) + 1;
                }
                request.setAttribute("LIST", list);
                request.setAttribute("SEARCH", search);
                session.setAttribute("TOTALPAGES", totalPages);
            } else {
                session.removeAttribute("TOTALPAGES");
                url = "GetArticleController";
            }
        } catch (Exception e) {
            log("ERROR at SearchController: " + e.getMessage());
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
