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
 * ShowUserServlet
 * created by Ksenya_Ushakova at 22.06.2020
 */
@WebServlet(urlPatterns = "/showuser", name = "ShowUserServlet")
public class ShowUserServlet extends HttpServlet {
    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login == null) {
            throw new ServletException("Missing parameter id");
        }
        User user = null;
        try {
            Optional<User> byLogin = userDao.getByLogin(login);
            if (byLogin.isPresent()) {
                user = byLogin.get();
            }
        } catch (Exception e) {
            throw new ServletException("Getting user data failed");
        }

        req.setAttribute("user", user);
        req.setAttribute("PageTitle", "Users");
        req.setAttribute("PageBody", "user/showuser.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }
}
