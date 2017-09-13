package ru.achrom.servlets;

import ru.achrom.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteClientServlet extends HttpServlet {
    private final ClientCache clientCache = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.clientCache.delete(this.clientCache.get(Integer.parseInt(req.getParameter("id"))));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}