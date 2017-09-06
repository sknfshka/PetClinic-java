package servlets.clinic;

import store.clinic.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.08.2017.
 */
public class ClinicViewServlet extends HttpServlet {

    private final ClientCache clientCache = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", this.clientCache.values());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/ClinicView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        clientCache.close();
    }
}
