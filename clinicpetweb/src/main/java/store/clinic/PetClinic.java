package store.clinic;

import lessons.lesson_6.Client;
import lessons.lesson_6.Clinic;
import lessons.lesson_6.ClinicStorageInterface;
import lessons.lesson_6.WrongInputDataException;

import java.util.Collection;

public class PetClinic implements ClinicStorageInterface {
    private static final PetClinic instance = new PetClinic();
    private final ClinicStorageInterface clinic = new JdbcStorage();

    public static PetClinic getInstance() {
        return instance;
    }

    @Override
    public Collection<Client> values() {
        return clinic.values();
    }

    @Override
    public int add(Client client) {
        return clinic.add(client);
    }

    @Override
    public void edit(Client client) {
        clinic.edit(client);
    }

    @Override
    public void delete(int id) {
        clinic.delete(id);
    }

    @Override
    public Client get(int id) {
        return clinic.get(id);
    }

    @Override
    public Collection<Client> findByName(String name) throws WrongInputDataException {
        return clinic.findByName(name);
    }

    @Override
    public Collection<Client> findByPetName(String petName) throws WrongInputDataException {
        return clinic.findByPetName(petName);
    }

    @Override
    public int generateId() {
        return clinic.generateId();
    }

    @Override
    public void close() {
    }
}
