package store.clinic;


import models.Animal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collection;

/**
 * Created by Animal on 27.08.2017.
 */
public class HibernateAnimalStorage implements AnimalStorage<Animal> {
    private final SessionFactory factory;

    public HibernateAnimalStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Animal> findUserAnimals(int id) {
        return transaction((Session session) -> {
            final Query query = session.createQuery("from Animal as pet where pet.clientId = :id");
            query.setInteger("id", id);
            return (Collection<Animal>) query.list();} );
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
            session.clear();
            session.close();
        }
    }

    @Override
    public Collection<Animal> values() {
        return transaction((Session session) -> session.createQuery("from Animal").list() );
    }

    @Override
    public int add(final Animal animal) {
        return transaction((Session session) -> {
            session.save(animal);
            return animal.getId();
        } );
    }

    @Override
    public void edit(Animal animal) {
        transaction((Session session) -> {session.update(animal); return null;});
    }

    @Override
    public void delete(Animal animal) {
        transaction((Session session) -> {session.delete(animal); return null;});
    }

    @Override
    public Animal get(int id) {
        return transaction((Session session) -> (Animal) session.get(Animal.class, id));
    }

    @Override
    public Collection<Animal> findByName(String name) {
        return transaction((Session session) -> {
            final Query query = session.createQuery("from Animal as pet where pet.name like :AnimalName");
            query.setString("AnimalName", "%"+name+"%");
            return (Collection<Animal>) query.list();} );
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
