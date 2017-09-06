package store.clinic;


import models.Client;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Client on 27.08.2017.
 */
public class HibernateClientStorage implements Storage<Client> {
    private final SessionFactory factory;

    public HibernateClientStorage() {
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
    public void delete(Client client) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new Client(client.getId()));
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
    public Collection<Client> findByName(String name) {
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
    public void close() {
        this.factory.close();
    }
}
