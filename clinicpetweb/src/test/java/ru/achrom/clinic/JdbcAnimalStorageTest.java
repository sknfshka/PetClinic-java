package ru.achrom.clinic;

import ru.achrom.models.Animal;
import ru.achrom.models.Client;
import org.junit.Test;
import ru.achrom.store.JdbcAnimalStorage;
import ru.achrom.store.JdbcClientStorage;

import java.util.Collection;

import static org.junit.Assert.*;

public class JdbcAnimalStorageTest {
    @Test
    public void add() throws Exception {
        final JdbcClientStorage clientStorage = new JdbcClientStorage();
        final JdbcAnimalStorage animalStorage = new JdbcAnimalStorage();
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
        final JdbcAnimalStorage animalStorage = new JdbcAnimalStorage();
        Collection<Animal> animals = animalStorage.values();
        assertFalse(animals.isEmpty());
    }

    @Test
    public void delete()throws Exception {
        final JdbcAnimalStorage animalStorage = new JdbcAnimalStorage();
        final JdbcClientStorage clientStorage = new JdbcClientStorage();
        Client client = new Client("Test");
        final int clientId = clientStorage.add(client);
        final int animalId = animalStorage.add(new Animal("Test", 777, "Cat", clientStorage.get(clientId)));
        animalStorage.delete(animalStorage.get(animalId));
        assertEquals(null, animalStorage.get(animalId));
        animalStorage.close();
        clientStorage.close();
    }

}