package store.clinic;

import models.WrongInputDataException;

import java.util.Collection;

public interface Storage<T> {
    public Collection<T> values();

    public int add(final T obj);

    public void edit(final T obj);

    public void delete(final T id);

    public T get(final int id);

    public Collection<T> findByName(final String name) throws WrongInputDataException;

    public void close();
}
