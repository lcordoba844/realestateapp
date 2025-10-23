package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;

        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("currentUser") != null);

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        boolean isLoginPage = uri.equals(contextPath + "/login.jsp");
        boolean isLoginServlet = uri.equals(contextPath + "/LoginServlet");
        boolean isCssOrJs = uri.startsWith(contextPath + "/assets/");

        if (loggedIn || isLoginPage || isLoginServlet || isCssOrJs) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(contextPath + "/login.jsp");
        }
    }

}
