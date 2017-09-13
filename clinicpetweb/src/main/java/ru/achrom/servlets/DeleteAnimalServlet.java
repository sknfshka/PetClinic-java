package ru.achrom.servlets;

import ru.achrom.store.AnimalCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteAnimalServlet extends HttpServlet {
    private final AnimalCache animalCache = AnimalCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.animalCache.delete(this.animalCache.get(Integer.parseInt(req.getParameter("id"))));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "edit-client?id=" + req.getParameter("clientId")));
    }
}