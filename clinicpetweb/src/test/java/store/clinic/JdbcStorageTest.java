package store.clinic;

import lessons.lesson_6.Client;
import lessons.lesson_6.Dog;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 27.08.2017.
 */
public class JdbcStorageTest {
    @Test
    public void add() throws Exception {
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new Client(-1, "Anna", new Dog("Anna")));
        final Client user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();
    }

    @Test
    public void values() throws Exception {
        final JdbcStorage storage = new JdbcStorage();
        Collection<Client> clients = storage.values();
        System.out.print(clients);
        assertFalse(clients.isEmpty());
    }

}