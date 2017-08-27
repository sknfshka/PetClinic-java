package lessons.lesson_6;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс клиника домашних животных
 */
public class Clinic implements ClinicStorageInterface {
    /**
     * Список клиентов
     */
    private List<Client> clients = new LinkedList<>();
    /**
     * Текущий идентификатор
     */
    private final AtomicInteger id = new AtomicInteger();

    /**
     * Удаление клиента
     * @param client клиент
     */
    public void deleteClient(Client client) {
        clients.remove(client);
    }

    @Override
    public String toString() {
        String result = "";

        for(Client client : clients) {
            result += client.toString() + "; ";
        }

        return result;
    }
    public List<Client> getClients() {
        return clients;
    }

    public AtomicInteger getId() {
        return id;
    }

    /**
     * Получить число клиентов в клинике
     * @return число клиентов в клинике
     */
    public int countOfClients() {
        return clients.size();
    }

    @Override
    public Collection<Client> values() {
        return getClients();
    }

    /**
     * Добавление нового клиента
     * @param client клиент
     * @return id клиента
     */
    @Override
    public int add(Client client) {
        if(!clients.contains(client)) {
            client.setId(Integer.toString(id.getAndIncrement()));
            clients.add(client);
        }

        return id.get();
    }

    /**
     * Редактирование клиента
     * @param client клиент
     */
    @Override
    public void edit(Client client) {
        if(!clients.contains(client)) {
            Client findClient = get(Integer.parseInt(client.getId()));
            findClient.copyFromClient(client);
        }
    }

    /**
     * Удаление клиента
     * @param id клиент
     */
    @Override
    public void delete(int id) {
        deleteClient(get(id));
    }

    /**
     * Поиск клиента id
     * @param id id клиента
     * @return найденный клиент
     */
    @Override
    public Client get(int id){
        for(Client client : clients) {
            if(Integer.parseInt(client.getId()) == id) {
                return client;
            }
        }

        return null;
    }

    /**
     * Поиск клиентов по имени клиента
     * @param name имя клиента
     * @return список найденных клиентов
     * @throws WrongInputDataException если пользователя с таким именем нет, бросаем исключение
     */
    @Override
    public Collection<Client> findByName(String name) throws WrongInputDataException {
        List<Client> result = new LinkedList<>();

        for(Client client : clients) {
            if(client.getName().equals(name)) {
                result.add(client);
            }
        }

        if(result.size() == 0) {
            throw new WrongInputDataException();
        }

        return result;
    }

    /**
     * Поиск клиентов по имени питомца
     * @param petName имя питомца
     * @return список найденных клиентов
     * @throws WrongInputDataException если пользователя с таким именем питомца нет, бросаем исключение
     */
    @Override
    public Collection<Client> findByPetName(String petName) throws WrongInputDataException {
        List<Client> result = new LinkedList<>();

        for(Client client : clients) {
            if(client.getPet().getName().equals(petName)) {
                result.add(client);
            }
        }

        if(result.size() == 0) {
            throw new WrongInputDataException();
        }

        return result;
    }

    @Override
    public int generateId() {
        return id.get();
    }

    @Override
    public void close() {
    }
}
