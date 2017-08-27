package servlets.clinic;

import lessons.lesson_6.Client;
import lessons.lesson_6.Dog;
import store.clinic.PetClinic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.08.2017.
 */
public class AddClientServlet extends HttpServlet {
    private final PetClinic clinic = PetClinic.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.clinic.add(new Client(req.getParameter("name"), new Dog(req.getParameter("petName"))));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/view"));
    }
}
