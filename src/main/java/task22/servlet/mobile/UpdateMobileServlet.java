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

/**
 * UpdateMobileServlet
 * created by Ksenya_Ushakova at 23.06.2020
 */

@WebServlet("/updateMobile")
public class UpdateMobileServlet extends HttpServlet {
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
            throw new ServletException("Update mobile data failed");
        }

        req.setAttribute("mobile", mobile);
        req.setAttribute("PageTitle", "Update mobile data");
        req.setAttribute("PageBody", "mobile/updateMobile.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(req.getParameter("id"));
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(id, model, Integer.valueOf(price), manufacturer);
        try {
            mobileDao.updateMobileById(mobile);
        } catch (Exception e) {
            throw new ServletException("Update mobile data failed");
        }

        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }
}
