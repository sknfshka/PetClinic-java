package lessons.lesson_6;

import lessons.lesson_6.Client;
import lessons.lesson_6.WrongInputDataException;

import java.util.Collection;

/**
 * Created by User on 27.08.2017.
 */
public interface ClinicStorageInterface {
    public Collection<Client> values();

    public int add(final Client user);

    public void edit(final Client user);

    public void delete(final int id);

    public Client get(final int id);

    public Collection<Client> findByName(final String name) throws WrongInputDataException;

    public Collection<Client> findByPetName(final String petName) throws WrongInputDataException;

    public int generateId();

    public void close();
}
