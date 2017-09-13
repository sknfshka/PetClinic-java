package ru.achrom.servlets;

import ru.achrom.store.AnimalCache;
import ru.achrom.store.ClientCache;
import ru.achrom.models.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.08.2017.
 */
public class EditClientServlet extends HttpServlet {
    private final ClientCache clientCache = ClientCache.getInstance();
    private final AnimalCache animalCache = AnimalCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", this.clientCache.get(Integer.valueOf(req.getParameter("id"))));
        req.setAttribute("animals", this.animalCache.findUserAnimals(Integer.valueOf(req.getParameter("id"))));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/EditClient.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.clientCache.edit(new Client(Integer.parseInt(req.getParameter("id")), req.getParameter("name")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}
