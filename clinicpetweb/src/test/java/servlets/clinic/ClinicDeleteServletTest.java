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
public class ClinicDeleteServletTest extends Mockito {
    final PetClinic petClinic = PetClinic.getInstance();

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
}
