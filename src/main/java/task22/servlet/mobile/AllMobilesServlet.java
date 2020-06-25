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
import java.util.List;


@WebServlet("/allmobiles")
public class AllMobilesServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Mobile> mobiles;
        try {
            mobiles = mobileDao.getAllMobile();
        } catch (Exception e) {
            throw new ServletException("Getting mobiles list failed");
        }
        req.setAttribute("mobiles", mobiles);
        req.setAttribute("PageTitle", "Mobiles");
        req.setAttribute("PageBody", "mobile/allmobiles.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }
}
