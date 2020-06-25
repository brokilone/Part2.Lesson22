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


@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New Users");
        req.setAttribute("PageBody", "user/formUser.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int rating = Integer.parseInt(req.getParameter("rating"));
        User user = new User(login, password, rating);
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            throw new ServletException("Add user failed");
        }

        resp.sendRedirect(req.getContextPath() + "/allUsers");
    }
}
