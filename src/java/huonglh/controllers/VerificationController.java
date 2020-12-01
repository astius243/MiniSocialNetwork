package huonglh.controllers;

import huonglh.blos.AccountBLO;
import huonglh.entities.Account;
import huonglh.entities.Status;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau Huong
 */
public class VerificationController extends HttpServlet {

    private static final String SUCCESS = "GetArticleController";
    private static final String INVALID = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            HttpSession session = request.getSession();
            String captcha = (String) session.getAttribute("CAPTCHA");
            String code = request.getParameter("txtCode");

            AccountBLO blo = new AccountBLO();
            Account acc = (Account) session.getAttribute("USER");
            if (code.equals(captcha)) {
                acc.setStatus(new Status(2));
                if (blo.updateStatus(acc)) {
                    url = SUCCESS;
                    session.setAttribute("USER", acc);
                    session.removeAttribute("CAPTCHA");
                    request.setAttribute("SUCCESS", "Verified Successfully");
                }
            } else {
                request.setAttribute("FAILED", "Invalid Code");
            }
        } catch (Exception e) {
            log("ERROR at VerificationController: " + e.getMessage());
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
