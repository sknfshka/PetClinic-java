package store.clinic;


import java.util.Collection;

public interface AnimalStorage<T> extends Storage<T> {
    public Collection<T> findUserAnimals(int id);
}
