package store.clinic;

import models.Animal;
import models.WrongInputDataException;

import java.util.Collection;

public class AnimalCache implements AnimalStorage<Animal> {
    private static final AnimalCache instance = new AnimalCache();
    private final AnimalStorage<Animal> animalStorage = new HibernateAnimalStorage();

    public static AnimalCache getInstance() {
        return instance;
    }

    @Override
    public Collection<Animal> values() {
        return animalStorage.values();
    }

    @Override
    public int add(Animal animal) {
        return animalStorage.add(animal);
    }

    @Override
    public void edit(Animal animal) {
        animalStorage.edit(animal);
    }

    @Override
    public void delete(Animal animal) {
        animalStorage.delete(animal);
    }

    @Override
    public Animal get(int id) {
        return animalStorage.get(id);
    }

    @Override
    public Collection<Animal> findByName(String name) throws WrongInputDataException {
        return animalStorage.findByName(name);
    }

    @Override
    public Collection<Animal> findUserAnimals(int id) {
        return animalStorage.findUserAnimals(id);
    }

    @Override
    public void close() {
    }
}
