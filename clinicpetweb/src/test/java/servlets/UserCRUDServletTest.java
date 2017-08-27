package servlets;

import junit.framework.Assert;
import org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import store.UserCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 26.08.2017.
 */
public class UserCRUDServletTest extends Mockito{
    final UserCache userCache = UserCache.getInstance();

    @Test
    public void createUser() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getParameter("login")).thenReturn("test");
        when(req.getParameter("email")).thenReturn("test");

        Assert.assertTrue(userCache.values().isEmpty());

        new UserCreateServlet().doPost(req, res);

        verify(req, atLeast(1)).getParameter("login");
        verify(req, atLeast(1)).getParameter("email");

        Assert.assertTrue(!userCache.values().isEmpty());
    }
}