package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;

        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("currentUser") != null);

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Allow login pages, login servlet, static assets
        boolean isLoginPage = uri.endsWith("login.jsp");
        boolean isLoginServlet = uri.endsWith("/Login");
        boolean isAssets = uri.startsWith(contextPath + "/assets/");

        if (loggedIn || isLoginPage || isLoginServlet || isAssets) {
            chain.doFilter(request, response); // pass through
        } else {
            response.sendRedirect(contextPath + "/login.jsp"); // not logged in
        }
    }
}