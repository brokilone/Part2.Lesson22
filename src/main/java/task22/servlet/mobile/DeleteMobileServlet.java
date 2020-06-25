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
import java.util.Optional;

/**
 * DeletMobileServlet
 * created by Ksenya_Ushakova at 23.06.2020
 */

@WebServlet("/deleteMobile")
public class DeleteMobileServlet extends HttpServlet {
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
        } catch (Exception e) {
            throw new ServletException("Delete mobile failed");
        }

        req.setAttribute("mobile", mobile);
        req.setAttribute("PageTitle", "Delete confirm");
        req.setAttribute("PageBody", "mobile/deleteMobile.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String id = req.getParameter("id");
        try {
            mobileDao.deleteMobileById(Integer.parseInt(id));
        } catch (Exception e) {
            throw new ServletException("Delete mobile failed");
        }

        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }
}
