package servlets.clinic;

import models.Animal;
import models.Client;
import store.clinic.AnimalCache;
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
public class EditAnimalServlet extends HttpServlet {
    private final AnimalCache animalCache = AnimalCache.getInstance();
    private final ClientCache clientCache = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userId", Integer.valueOf(req.getParameter("clientId")));
        req.setAttribute("animal", animalCache.get(Integer.valueOf(req.getParameter("id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/clinic/EditAnimal.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Animal animal = new Animal();
        animal.setId(Integer.parseInt(req.getParameter("id")));
        animal.setName(req.getParameter("name"));
        animal.setAge(Integer.parseInt(req.getParameter("age")));
        animal.setOwner(clientCache.get(Integer.parseInt(req.getParameter("userId"))));

        if(req.getParameter("kind").equals("Dog")) {
            animal.setKind("Dog");
        }
        else {
            animal.setKind("Cat");
        }

        this.animalCache.edit(animal);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "edit-client?id=" + req.getParameter("userId")));
    }
}
