package servlets.clinic;

import models.Animal;
import store.clinic.AnimalCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnimalServlet extends HttpServlet {
    private final AnimalCache animalCache = AnimalCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userId", Integer.valueOf(req.getParameter("clientId")));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/AddAnimal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Animal animal = new Animal();
        animal.setName(req.getParameter("name"));
        animal.setAge(Integer.parseInt(req.getParameter("age")));
        animal.setClientId(Integer.parseInt(req.getParameter("userId")));

        if(req.getParameter("kind").equals("Dog")) {
            animal.setKind("Dog");
        }
        else {
            animal.setKind("Cat");
        }

        this.animalCache.add(animal);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "edit-client?id=" + animal.getClientId()));
    }
}
