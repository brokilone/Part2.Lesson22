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
import java.util.Optional;

/**
 * UpdateMobileServlet
 * created by Ksenya_Ushakova at 23.06.2020
 */

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login == null) {
            throw new ServletException("Missing parameter login");
        }
        User user = null;
        try {
            Optional<User> byLogin = userDao.getByLogin(login);
            if (byLogin.isPresent()) {
                user = byLogin.get();
            }
        } catch (Exception e) {
            throw new ServletException("Update user data failed");
        }

        req.setAttribute("user", user);
        req.setAttribute("PageTitle", "Update user data");
        req.setAttribute("PageBody", "user/updateUser.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int rating = Integer.parseInt(req.getParameter("rating"));
        User user = new User(login,password,rating);
        try {
            userDao.updateByLogin(user);
        } catch (Exception e) {
            throw new ServletException("Update user data failed");
        }

        resp.sendRedirect(req.getContextPath() + "/allUsers");
    }
}
