package ru.achrom.store;


import java.util.Collection;

public interface AnimalStorage<T> extends Storage<T> {
    Collection<T> findUserAnimals(int id);
}
