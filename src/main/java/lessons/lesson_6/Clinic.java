package lessons.lesson_6;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс клиника домашних животных
 */
public class Clinic {
    /**
     * Список клиентов
     */
    private List<Client> clients = new LinkedList<>();
    /**
     * Текущий идентификатор
     */
    private int id = 0;

    /**
     * Добавление нового клиента
     * @param client клиент
     */
    public void addClient(Client client) {
        if(!clients.contains(client)) {
            client.setId(Integer.toString(id++));
            clients.add(client);
        }
    }

    /**
     * Поиск клиентов по имени клиента
     * @param name имя клиента
     * @return список найденных клиентов
     * @throws WrongInputDataException если пользователя с таким именем нет, бросаем исключение
     */
    public List<Client> findClientsByClientName(String name) throws WrongInputDataException {
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
     * @param name имя питомца
     * @return список найденных клиентов
     * @throws WrongInputDataException если пользователя с таким именем питомца нет, бросаем исключение
     */
    public List<Client> findClientsByPetName(String name) throws WrongInputDataException {
        List<Client> result = new LinkedList<>();

        for(Client client : clients) {
            if(client.getPet().getName().equals(name)) {
                result.add(client);
            }
        }

        if(result.size() == 0) {
            throw new WrongInputDataException();
        }

        return result;
    }

    @Override
    public String toString() {
        String result = "";

        for(Client client : clients) {
            result += client.toString() + "; ";
        }

        return result;
    }

    /**
     * Получить число клиентов в клинике
     * @return число клиентов в клинике
     */
    public int countOfClients() {
        return clients.size();
    }
}
