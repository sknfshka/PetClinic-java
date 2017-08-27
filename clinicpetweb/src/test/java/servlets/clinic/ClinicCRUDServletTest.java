package servlets.clinic;

import junit.framework.Assert;
import lessons.lesson_6.Client;
import lessons.lesson_6.Dog;
import org.mockito.Mockito;
import org.junit.Test;
import store.clinic.PetClinic;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.08.2017.
 */
public class ClinicCRUDServletTest extends Mockito{
    final PetClinic petClinic = PetClinic.getInstance();

    @Test
    public void createClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getParameter("name")).thenReturn("test");
        when(req.getParameter("petName")).thenReturn("test");

        Assert.assertTrue(petClinic.values().isEmpty());

        new AddClientServlet().doPost(req, res);

        verify(req, atLeast(1)).getParameter("name");
        verify(req, atLeast(1)).getParameter("petName");

        Assert.assertFalse(petClinic.values().isEmpty());
    }

    @Test
    public void deleteClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");

        petClinic.add(new Client("test", new Dog("test")));
        Assert.assertFalse(petClinic.values().isEmpty());

        new DeleteClientServlet().doGet(req, res);

        verify(req, atLeast(1)).getParameter("id");

        Assert.assertTrue(petClinic.values().isEmpty());
    }

    @Test
    public void editClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("test");
        when(req.getParameter("petName")).thenReturn("test");

        petClinic.add(new Client("temp", new Dog("temp")));
        Assert.assertFalse(petClinic.values().isEmpty());

        int countBeforeEdit = petClinic.values().size();

        new EditClientServlet().doPost(req, res);

        verify(req, atLeast(1)).getParameter("id");
        verify(req, atLeast(1)).getParameter("name");
        verify(req, atLeast(1)).getParameter("petName");

        Assert.assertEquals(countBeforeEdit, petClinic.values().size());
        Assert.assertEquals("test", petClinic.get(0).getName());
        Assert.assertEquals("test", petClinic.get(0).getPetName());
    }
}