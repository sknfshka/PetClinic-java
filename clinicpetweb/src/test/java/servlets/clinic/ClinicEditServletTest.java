package servlets.clinic;

import junit.framework.Assert;
import lessons.lesson_6.Client;
import lessons.lesson_6.Dog;
import org.junit.Test;
import org.mockito.Mockito;
import store.clinic.PetClinic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 27.08.2017.
 */
public class ClinicEditServletTest extends Mockito {
    final PetClinic petClinic = PetClinic.getInstance();

    @Test
    public void editClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("test");
        when(req.getParameter("petName")).thenReturn("test");
        when(req.getParameter("kind")).thenReturn("Cat");

        petClinic.add(new Client("temp", new Dog("temp")));
        Assert.assertFalse(petClinic.values().isEmpty());

        int countBeforeEdit = petClinic.values().size();

        new EditClientServlet().doPost(req, res);

        verify(req, atLeast(1)).getParameter("id");
        verify(req, atLeast(1)).getParameter("name");
        verify(req, atLeast(1)).getParameter("petName");
        verify(req, atLeast(1)).getParameter("kind");

        Assert.assertEquals(countBeforeEdit, petClinic.values().size());
        Assert.assertEquals("test", petClinic.get(0).getName());
        Assert.assertEquals("test", petClinic.get(0).getPetName());
        Assert.assertEquals("Cat", petClinic.get(0).getPetKind());
    }
}