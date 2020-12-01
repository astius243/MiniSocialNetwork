package huonglh.controllers;

import huonglh.blos.EmotionBLO;
import huonglh.blos.NotificationBLO;
import huonglh.entities.Account;
import huonglh.entities.Article;
import huonglh.entities.Emotion;
import huonglh.entities.Notification;
import java.io.IOException;
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
public class EmotionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Article art = (Article) session.getAttribute("ARTICLE");
            Account acc = (Account) session.getAttribute("USER");
            EmotionBLO blo = new EmotionBLO();
            Emotion emo = blo.checkEmotion(acc.getEmail(), art.getArticleID());
            String emotion = request.getParameter("emotion");
            String content = null;
            boolean check = false;
            if (!acc.getRole().equals("Admin")) {
                if (emo != null) {
                    if (emo.getEmotion().equals("LIKE")) {
                        if (emotion.equals("like")) {
                            check = false;
                            emo.setEmotion("UNKNOWN");
                            System.out.println("LIKE-UNKNOWN");
                        } else {
                            emo.setEmotion(emotion.toUpperCase());
                            check = true;
                            content = acc.getName() + " " + emo.getEmotion().toLowerCase() + " " + art.getTitle();
                        }
                    } else if (emo.getEmotion().equals("DISLIKE")) {
                        if (emotion.equals("dislike")) {
                            emo.setEmotion("UNKNOWN");
                            check = false;
                            System.out.println("DISLIKE-UNKNOWN");
                        } else {
                            emo.setEmotion(emotion.toUpperCase());
                            content = acc.getName() + " " + emo.getEmotion().toLowerCase() + " " + art.getTitle();
                            check = true;
                        }
                    } else {
                        emo.setEmotion(emotion.toUpperCase());
                        content = acc.getName() + " " + emo.getEmotion().toLowerCase() + " " + art.getTitle();
                        check = true;
                    }
                    blo.updateEmotion(emo);
                } else {
                    emo = new Emotion(acc, art);
                    if (emotion.equals("like")) {
                        check = true;
                        emo.setEmotion("LIKE");
                        content = acc.getName() + " " + emo.getEmotion().toLowerCase() + " " + art.getTitle();
                        System.out.println("NEW-LIKE");
                    } else {
                        check = true;
                        emo.setEmotion("DISLIKE");
                        content = acc.getName() + " " + emo.getEmotion().toLowerCase() + " " + art.getTitle();
                        System.out.println("NEW-DISLIKE");
                    }
                    blo.insertEmotion(emo);
                }
                if (check && (acc.getEmail().compareTo(art.getEmail().getEmail()) != 0)) {
                    Date date = Calendar.getInstance().getTime();
                    Notification noti = new Notification(date, content, art);
                    NotificationBLO notiBLO = new NotificationBLO();
                    notiBLO.insertNotify(noti);
                }
            } else {
                request.setAttribute("FAILED", "Admin can't react to article");
            }
        } catch (Exception e) {
            log("ERROR at EmotionController: " + e.getMessage());
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
