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


@WebServlet("/addmobile")
public class AddMobileServlet extends HttpServlet {
    @Inject
    private MobileDao mobileDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "New Mobiles");
        req.setAttribute("PageBody", "mobile/formMobile.jsp");
        req.getRequestDispatcher("WEB-INF/jsp/layout.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(0, model, Integer.valueOf(price), manufacturer);
        try {
            mobileDao.addMobile(mobile);
        } catch (Exception e) {
            throw new ServletException("Add mobile failed");
        }

        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }
}
