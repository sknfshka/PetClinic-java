package store.clinic;

import models.Animal;
import models.Client;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by User on 27.08.2017.
 */
public class HibernateAnimalStorageTest {
    @Test
    public void add() throws Exception {
        final HibernateClientStorage clientStorage = new HibernateClientStorage();
        final HibernateAnimalStorage animalStorage = new HibernateAnimalStorage();
        Client client = new Client("Andrew");
        final int clientId = clientStorage.add(client);
        final int animalId = animalStorage.add(new Animal("Ashley", 3, "Cat", clientStorage.get(clientId)));
        final Animal animal = animalStorage.get(animalId);
        assertEquals(animal.getOwner().getId(), clientId);
        clientStorage.close();
        animalStorage.close();
    }

    @Test
    public void values() throws Exception {
        final HibernateAnimalStorage animalStorage = new HibernateAnimalStorage();
        Collection<Animal> animals = animalStorage.values();
        assertFalse(animals.isEmpty());
    }

    @Test
    public void delete()throws Exception {
        final HibernateAnimalStorage animalStorage = new HibernateAnimalStorage();
        final HibernateClientStorage clientStorage = new HibernateClientStorage();
        Client client = new Client("Test");
        final int clientId = clientStorage.add(client);
        final int animalId = animalStorage.add(new Animal("Test", 777, "Cat", clientStorage.get(clientId)));
        animalStorage.delete(animalStorage.get(animalId));
        assertEquals(null, animalStorage.get(animalId));
        animalStorage.close();
        clientStorage.close();
    }

}