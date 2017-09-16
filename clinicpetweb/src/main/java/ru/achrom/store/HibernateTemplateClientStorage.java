package ru.achrom.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.achrom.models.Client;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class HibernateTemplateClientStorage implements IHTClientStorage {
    private final HibernateTemplate template;

    @Autowired
    public HibernateTemplateClientStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Client> values() {
        return (List<Client>) this.template.find("from Client");
    }

    @Override
    public int add(final Client client) {
        return (int) this.template.save(client);
    }

    @Override
    public void edit(Client client) {
        this.template.update(client);
    }

    @Override
    public void delete(Client client) {
        this.template.delete(client);
    }

    @Override
    public Client get(int id) {
        return this.template.get(Client.class, id);
    }

    @Override
    public Collection<Client> findByName(String name) {
        return (Collection<Client>) this.template.findByNamedParam("from Client as client where client.name like :clientName"
        ,"clientName","%"+name+"%");
    }

    @Override
    public void close() {}
}
