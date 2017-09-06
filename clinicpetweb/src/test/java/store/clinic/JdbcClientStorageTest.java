package store.clinic;

import models.Animal;
import models.Client;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by User on 27.08.2017.
 */
public class JdbcClientStorageTest {
    @Test
    public void add() throws Exception {
        final JdbcClientStorage storage = new JdbcClientStorage();
        Client client = new Client("Nick");
        client.addAnimal(new Animal("Nick", 11, Animal.Kind.CAT));
        final int id = storage.add(client);
        final Client user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();
    }

    @Test
    public void values() throws Exception {
        final JdbcClientStorage storage = new JdbcClientStorage();
        Collection<Client> clients = storage.values();
        assertFalse(clients.isEmpty());
        storage.close();
    }

    @Test
    public void delete()throws Exception {
        final JdbcClientStorage storage = new JdbcClientStorage();
        Client client = new Client("Nick");
        client.addAnimal(new Animal("Nick", 11, Animal.Kind.CAT));
        storage.add(client);

        Collection<Client> clients = storage.findByName("Nick");
        assertFalse(clients.isEmpty());

        for (Client cl : clients) {
            storage.delete(cl);
        }

        assertTrue( clients.size() > storage.findByName("Nick").size() );
        storage.close();
    }

}