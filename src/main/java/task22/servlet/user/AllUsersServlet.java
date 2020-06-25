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
import java.util.List;

/**
 * AllUsersServlet
 * created by Ksenya_Ushakova at 22.06.2020
 */
@WebServlet(urlPatterns = "/allUsers", name = "AllUsersServlet")
public class AllUsersServlet extends HttpServlet {
    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        try {
            users = userDao.getAllUsers();
        } catch (Exception e) {
            throw new ServletException("Getting user's list failed");
        }
        req.setAttribute("users", users);
        req.setAttribute("PageTitle", "Users");
        req.setAttribute("PageBody", "user/allUsers.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }
}
