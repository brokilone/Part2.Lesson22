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
 * DeletMobileServlet
 * created by Ksenya_Ushakova at 23.06.2020
 */

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
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
            throw new ServletException("Delete user failed");
        }

        req.setAttribute("user", user);
        req.setAttribute("PageTitle", "Delete confirm");
        req.setAttribute("PageBody", "user/deleteUser.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login  = req.getParameter("login");
        try {
            userDao.deleteByLogin(login);
        } catch (Exception e) {
            throw new ServletException("Delete user failed");
        }

        resp.sendRedirect(req.getContextPath() + "/allUsers");
    }
}
