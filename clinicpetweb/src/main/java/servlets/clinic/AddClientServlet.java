package servlets.clinic;

import models.Animal;
import models.Client;
import store.clinic.ClientCache;

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

        if(req.getParameter("kind").equals("Dog")) {
            client.addAnimal(new Animal(req.getParameter("petName"), Integer.parseInt(req.getParameter("age")), Animal.Kind.DOG));
        }
        else {
            client.addAnimal(new Animal(req.getParameter("petName"), Integer.parseInt(req.getParameter("age")), Animal.Kind.CAT));
        }

        this.clinic.add(client);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}
