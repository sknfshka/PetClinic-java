package ru.achrom.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.achrom.models.Animal;
import ru.achrom.models.Client;
import ru.achrom.store.Storages;
import ru.achrom.models.WrongInputDataException;

import java.util.Collection;

public class DbTool {
    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    private Storages storages = context.getBean(Storages.class);

    public static void main(String args[]) throws WrongInputDataException{
        DbTool DbTool = new DbTool();
        /*int id = DbTool.addClient(new Client("Kate"));
        System.out.println(DbTool.findByName("Kate"));
        DbTool.deleteClient(new Client(id));
        System.out.println(DbTool.getClients());*/
        System.out.println(DbTool.getAniamls());
    }

    public Collection<Client> getClients() {
        return storages.clientStorage.values();
    }

    public Collection<Animal> getAniamls() {
        return storages.animalStorage.values();
    }

    public int addClient(Client client) {
        return storages.clientStorage.add(client);
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

    public Collection<Client> findByName(String name) throws WrongInputDataException{
        return storages.clientStorage.findByName(name);
    }
}
