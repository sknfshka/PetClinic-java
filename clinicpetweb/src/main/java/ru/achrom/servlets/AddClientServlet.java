package ru.achrom.servlets;

import ru.achrom.store.ClientCache;
import ru.achrom.models.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.08.2017.
 */
public class AddClientServlet extends HttpServlet {
    private final ClientCache clinic = ClientCache.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client(req.getParameter("name"));
        this.clinic.add(client);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}
