package store.clinic;

import models.Client;
import models.WrongInputDataException;

import java.util.Collection;

public class ClientCache implements Storage<Client> {
    private static final ClientCache instance = new ClientCache();
    private final Storage<Client> clientStorage = new JdbcClientStorage();

    public static ClientCache getInstance() {
        return instance;
    }

    @Override
    public Collection<Client> values() {
        return clientStorage.values();
    }

    @Override
    public int add(Client client) {
        return clientStorage.add(client);
    }

    @Override
    public void edit(Client client) {
        clientStorage.edit(client);
    }

    @Override
    public void delete(Client client) {
        clientStorage.delete(client);
    }

    @Override
    public Client get(int id) {
        return clientStorage.get(id);
    }

    @Override
    public Collection<Client> findByName(String name) throws WrongInputDataException {
        return clientStorage.findByName(name);
    }

    @Override
    public void close() {
    }
}
