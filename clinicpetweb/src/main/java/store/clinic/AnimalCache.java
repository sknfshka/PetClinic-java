package store.clinic;

import models.Animal;
import models.Client;
import models.WrongInputDataException;

import java.util.Collection;

public class AnimalCache implements Storage<Animal> {
    private static final AnimalCache instance = new AnimalCache();
    private final Storage<Animal> clientStorage = new JdbcAnimalStorage();

    public static AnimalCache getInstance() {
        return instance;
    }

    @Override
    public Collection<Animal> values() {
        return clientStorage.values();
    }

    @Override
    public int add(Animal animal) {
        return clientStorage.add(animal);
    }

    @Override
    public void edit(Animal animal) {
        clientStorage.edit(animal);
    }

    @Override
    public void delete(Animal animal) {
        clientStorage.delete(animal);
    }

    @Override
    public Animal get(int id) {
        return clientStorage.get(id);
    }

    @Override
    public Collection<Animal> findByName(String name) throws WrongInputDataException {
        return clientStorage.findByName(name);
    }

    @Override
    public void close() {
    }
}
