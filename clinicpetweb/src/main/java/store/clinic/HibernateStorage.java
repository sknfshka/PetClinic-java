package store.clinic;

import lessons.lesson_6.Client;
import lessons.lesson_6.WrongInputDataException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import lessons.lesson_6.ClinicStorageInterface;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Client on 27.08.2017.
 */
public class HibernateStorage implements ClinicStorageInterface {
    private final SessionFactory factory;

    public HibernateStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<Client> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Client").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int add(final Client user) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            return user.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void edit(Client user) {

    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new Client(id, null, null));
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Client get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (Client) session.get(Client.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Collection<Client> findByName(String name) throws WrongInputDataException {
        Collection<Client> result = new ArrayList<>();

        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Client as user where user.name=:name");
            query.setString("name", name);
            result.add((Client) query.iterate().next());
        } finally {
            tx.commit();
            session.close();
        }

        return result;
    }

    @Override
    public Collection<Client> findByPetName(String petName) throws WrongInputDataException {
        return null;
    }
    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}
