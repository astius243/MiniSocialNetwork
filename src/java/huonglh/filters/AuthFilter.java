/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.filters;

import huonglh.entities.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau Huong
 */
public class AuthFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    private final List<String> admin;
    private final List<String> guest;
    private final List<String> member;

    public AuthFilter() {
        admin = new ArrayList<>();
        admin.add("article.jsp");
        admin.add("main.jsp");
        admin.add("register.jsp");
        admin.add("verify.jsp");
        admin.add("BackController");
        admin.add("DeleteArticleController");
        admin.add("DeleteCommentController");
        admin.add("GetArticleController");
        admin.add("LogoutController");
        admin.add("MainController");
        admin.add("SearchController");
        admin.add("SendEmailController");
        admin.add("VerificationController");
        admin.add("ViewArticleController");
        admin.add("MainController");
        admin.add("Login");
        admin.add("Register");
        admin.add("GetArticle");
        admin.add("ViewArticle");
        admin.add("Comment");
        admin.add("Search");
        admin.add("Emotion");
        admin.add("Verify");
        admin.add("DeleteArticle");
        admin.add("DeleteComment");
        admin.add("Back");
        admin.add("Logout");

        member = new ArrayList<>();
        member.add("article.jsp");
        member.add("main.jsp");
        member.add("register.jsp");
        member.add("verify.jsp");
        member.add("BackController");
        member.add("CommentController");
        member.add("DeleteArticleController");
        member.add("DeleteCommentController");
        member.add("EmotionController");
        member.add("GetArticleController");
        member.add("LogoutController");
        member.add("MainController");
        member.add("PostController");
        member.add("SearchController");
        member.add("SendEmailController");
        member.add("VerificationController");
        member.add("ViewArticleController");
        admin.add("MainController");
        admin.add("Login");
        admin.add("Register");
        admin.add("GetArticle");
        admin.add("ViewArticle");
        admin.add("Comment");
        admin.add("Search");
        admin.add("Emotion");
        admin.add("Verify");
        admin.add("DeleteArticle");
        admin.add("DeleteComment");
        admin.add("Back");
        admin.add("Logout");

        guest = new ArrayList<>();
        guest.add("index.jsp");
        guest.add("register.jsp");
        guest.add("");
        guest.add("MainController");
        guest.add("LoginController");
        guest.add("RegisterController");
        guest.add("Register");
        guest.add("Login");
        guest.add("''");

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        int index = uri.lastIndexOf("/");
        String[] resources = uri.substring(index + 1).split(";");
        String resource = resources[0];
        HttpSession session = req.getSession();

        if (uri.endsWith("css") || uri.endsWith("jpg") || uri.endsWith("JPG") || uri.equals("js")) {
            chain.doFilter(request, response);
            return;
        }
        String action = req.getParameter("action");
        if (session == null || session.getAttribute("USER") == null) {
            if ((guest.contains(resource) && guest.contains(action)) || (guest.contains(resource) && action == null)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("index.jsp");
            }
        } else {
            Account acc = (Account) session.getAttribute("USER");
            if (acc.getRole().equals("Admin")) {
                if (admin.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("MainController?action=GetArticle");
                }
            } else if (acc.getRole().equals("Member")) {
                if (member.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("MainController?action=GetArticle");
                }
            }
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
