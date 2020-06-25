package task22.servlet.mobile;


import task22.dao.mobile.MobileDao;
import task22.model.Mobile;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/showmobile")
public class ShowMobileServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = null;
        try {
            Optional<Mobile> mobileById = mobileDao.getMobileById(Integer.parseInt(mobileId));
            if (mobileById.isPresent()) {
                mobile = mobileById.get();
            }
        } catch (SQLException e) {
            throw new ServletException("Getting mobile data failed");
        }

        req.setAttribute("mobile", mobile);
        req.setAttribute("PageTitle", "Mobiles");
        req.setAttribute("PageBody", "mobile/showmobile.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }
}
