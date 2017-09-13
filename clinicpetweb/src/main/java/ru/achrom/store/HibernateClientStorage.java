package ru.achrom.store;


import org.springframework.stereotype.Repository;
import ru.achrom.models.Client;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collection;

@Repository
public class HibernateClientStorage implements Storage<Client> {
    private final SessionFactory factory;

    public HibernateClientStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Шаблон проектирования Commander
     * Интерфейс Command, представляющий нашу команду для выполнения
     * содержит только один метод process, который запускается для исполнения
     * Метод process мы будем реализовывать в анонимном классе передвавемом в функцию transaction
     * @param <T> тип-параметр, указывает на возвращаемый
     */
    public interface Command<T> {
        T process(Session session);
    }

    /**
     * Шаблон проектирования Commander
     * Функция-коммандер для обращения к БД
     * открывает сессию и транзакцию
     * выполняет в блоке try переданную команду и возвращает результат
     * после чего finally закрывает сессию и транзакцию
     *
     * @param command Команда
     * @param <T> Тип-параметр
     * @return результат работа команды
     */
    private <T> T transaction(final Command<T> command){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return command.process(session);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Collection<Client> values() {
        return transaction((Session session) -> session.createQuery("from Client").list() );
    }

    @Override
    public int add(final Client client) {
        return transaction((Session session) -> {
            session.save(client);
            return client.getId();
        } );
    }

    @Override
    public void edit(Client client) {
        transaction((Session session) -> {session.update(client); return null;});
    }

    @Override
    public void delete(Client client) {
        transaction((Session session) -> {session.delete(client); return null;});
    }

    @Override
    public Client get(int id) {
        return transaction((Session session) -> (Client) session.get(Client.class, id));
    }

    @Override
    public Collection<Client> findByName(String name) {
        return transaction((Session session) -> {
            final Query query = session.createQuery("from Client as client where client.name like :clientName");
            query.setString("clientName", "%"+name+"%");
            return (Collection<Client>) query.list();} );
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
