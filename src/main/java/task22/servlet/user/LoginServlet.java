package task22.servlet.user;

import task22.dao.user.UserDao;
import task22.model.User;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * LoginServlet
 * created by Ksenya_Ushakova at 23.06.2020
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("PageTitle", "Authentication required");
        request.setAttribute("PageBody", "login.jsp");
        request.setAttribute("attributes", "");
        request.getRequestDispatcher("WEB-INF/jsp/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Map<String, String> attributes = new HashMap<>();

        if (login == null || login.isEmpty()) {
            attributes.put("login", "Please enter login");
        }

        if (password == null || password.isEmpty()) {
            attributes.put("password", "Please enter password");
        }

        if (attributes.isEmpty()) {
            User user = null;
            try {
                Optional<User> byLogin = userDao.getByLogin(login);
                if (byLogin.isPresent()) {
                    user = byLogin.get();
                }
            } catch (Exception e) {
                throw new ServletException("Login failed");
            }
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("PageTitle", "Access denied, unknown login/password pair");
                request.setAttribute("PageBody", "error.jsp");
                request.getRequestDispatcher("WEB-INF/jsp/layout.jsp").forward(request, response);
            }
            return;
        }

        request.setAttribute("attributes", attributes);
        request.setAttribute("PageTitle", "Authentication required");
        request.setAttribute("PageBody", "login.jsp");
        request.getRequestDispatcher("WEB-INF/jsp/layout.jsp").forward(request, response);
    }


}

