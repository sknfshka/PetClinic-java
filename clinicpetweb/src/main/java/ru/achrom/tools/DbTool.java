package ru.achrom.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.achrom.models.Client;
import ru.achrom.store.Storages;

import java.util.Collection;

public class DbTool {
    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    private Storages storages = context.getBean(Storages.class);

    public static void main(String args[]) {
        DbTool DbTool = new DbTool();
        System.out.print(DbTool.getClients());
        DbTool.addClient(new Client("Bobby"));
        System.out.print(DbTool.getClients());
    }

    public Collection<Client> getClients() {
        return storages.clientStorage.values();
    }

    public void addClient(Client client) {
        storages.clientStorage.add(client);
    }

    public void editClient(Client client) {
        storages.clientStorage.edit(client);
    }

    public void deleteClient(Client client) {
        storages.clientStorage.delete(client);
    }

    public Client get(int id) {
        return storages.clientStorage.get(id);
    }

    public Collection<Client> findByName(String name) {
        return storages.clientStorage.findByName(name);
    }
}
