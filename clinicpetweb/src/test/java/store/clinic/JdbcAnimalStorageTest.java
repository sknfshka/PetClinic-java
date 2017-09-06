package store.clinic;

import models.Animal;
import models.Client;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by User on 27.08.2017.
 */
public class JdbcAnimalStorageTest {
    @Test
    public void add() throws Exception {
        final JdbcClientStorage clientStorage = new JdbcClientStorage();
        final JdbcAnimalStorage animalStorage = new JdbcAnimalStorage();
        Client client = new Client("Andrew");
        final int clientId = clientStorage.add(client);
        final int animalId = animalStorage.add(new Animal("Ashley", 3, Animal.Kind.CAT, clientId));
        final Client clientDataFromDataBase = clientStorage.get(clientId);
        assertEquals(animalId, clientDataFromDataBase.getAnimals().iterator().next().getId());
        clientStorage.close();
        animalStorage.close();
    }

    @Test
    public void values() throws Exception {
        final JdbcAnimalStorage animalStorage = new JdbcAnimalStorage();
        Collection<Animal> animals = animalStorage.values();
        assertFalse(animals.isEmpty());
    }

    @Test (expected = IllegalStateException.class)
    public void delete()throws Exception {
        final JdbcAnimalStorage animalStorage = new JdbcAnimalStorage();
        final JdbcClientStorage clientStorage = new JdbcClientStorage();
        Client client = new Client("Test");
        final int clientId = clientStorage.add(client);
        final int animalId = animalStorage.add(new Animal("Test", 3, Animal.Kind.CAT, clientId));
        animalStorage.delete(animalStorage.get(animalId));
        animalStorage.get(animalId);
        animalStorage.close();
        clientStorage.close();
    }

}