package ru.achrom.clinic;

import ru.achrom.models.Client;
import org.junit.Test;
import ru.achrom.store.HibernateClientStorage;

import java.util.Collection;

import static org.junit.Assert.*;

public class HibernateClientStorageTest {
    @Test
    public void add() throws Exception {
        final HibernateClientStorage storage = new HibernateClientStorage();
        Client client = new Client("Nick");
        final int id = storage.add(client);
        final Client user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();
    }

    @Test
    public void values() throws Exception {
        final HibernateClientStorage storage = new HibernateClientStorage();
        Collection<Client> clients = storage.values();
        assertFalse(clients.isEmpty());
        storage.close();
    }

    @Test
    public void delete()throws Exception {
        final HibernateClientStorage storage = new HibernateClientStorage();
        Client client = new Client("Nick");
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