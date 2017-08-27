package servlets;

import lessons.lesson_6.Animal;
import lessons.lesson_6.Dog;
import lessons.lesson_6.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO: comment
 * @author parsentev
 * @since 16.04.2015
 */
public class ClinicServlet extends HttpServlet {

    private final List<Pet> pets = new CopyOnWriteArrayList<>();
    private String nameForFind;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Name : <input type='text' name='name'>"+
                        "         Age : <input type='text' name='age'>"+
                        "         <input type='submit' value='add new Pet'>"+
                        "     <form>"+
                        this.viewPets() +
                        "<br>" +
                        "<br>" +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Name : <input type='text' name='find'>"+
                        "         <input type='submit' value='findByPetName'>"+
                        "     <form>"+
                        this.findPets() +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("name").isEmpty() && !req.getParameter("age").isEmpty())
            this.pets.add(new Dog(new Animal(req.getParameter("name"), Integer.parseInt(req.getParameter("age")))));
        if (!req.getParameter("find").isEmpty())
            this.nameForFind = req.getParameter("find");
        doGet(req, resp);
    }

    private String viewPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets</p>");
        sb.append("<ul>");
        for (Pet pet : this.pets) {
            sb.append("<li>").append(pet.getName()).append(" (").append(pet.getAge()).append(" years)").append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
    private String findPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Search result</p>");
        sb.append("<ul>");
        for (Pet pet : this.pets) {
            if(pet.getName().equals(this.nameForFind))
                sb.append("<li>").append(pet.getName()).append(" (").append(pet.getAge()).append(" years)").append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
}