package servlets.clinic;

import junit.framework.Assert;
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
public class ClinicCreateServletTest extends Mockito{
    final PetClinic petClinic = PetClinic.getInstance();

    @Test
    public void createClient() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getParameter("name")).thenReturn("test");
        when(req.getParameter("petName")).thenReturn("test");
        when(req.getParameter("kind")).thenReturn("Dog");

        Assert.assertTrue(petClinic.values().isEmpty());

        new AddClientServlet().doPost(req, res);

        verify(req, atLeast(1)).getParameter("name");
        verify(req, atLeast(1)).getParameter("petName");
        verify(req, atLeast(1)).getParameter("kind");

        Assert.assertFalse(petClinic.values().isEmpty());
    }
}