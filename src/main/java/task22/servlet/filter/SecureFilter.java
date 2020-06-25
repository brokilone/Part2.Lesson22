package task22.servlet.filter;

import task22.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SecureFilter
 * created by Ksenya_Ushakova at 23.06.2020
 */
@WebFilter("/*")
public class SecureFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String loginURI = request.getContextPath() + "/login";

        boolean logIn = (session != null) && (session.getAttribute("user") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (logIn || loginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }
}
