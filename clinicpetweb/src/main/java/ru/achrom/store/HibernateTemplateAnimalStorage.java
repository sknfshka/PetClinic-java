package ru.achrom.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.achrom.models.Animal;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class HibernateTemplateAnimalStorage implements IHTAnimalStorage {
    private final HibernateTemplate template;

    @Autowired
    public HibernateTemplateAnimalStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Animal> findUserAnimals(int id) {
        return (Collection<Animal>) this.template.findByNamedParam("from Animal as pet where pet.owner.id = :clientId"
                ,"clientId", id);
    }

    @Override
    public Collection<Animal> values() {
        return (List<Animal>) this.template.find("from Animal");
    }

    @Override
    public int add(final Animal animal) {
        return (int) this.template.save(animal);
    }

    @Override
    public void edit(Animal animal) {
        this.template.update(animal);
    }

    @Override
    public void delete(Animal animal) {
        this.template.delete(animal);
    }

    @Override
    public Animal get(int id) {
        return this.template.get(Animal.class, id);
    }

    @Override
    public Collection<Animal> findByName(String name) {
        return (Collection<Animal>) this.template.findByNamedParam("from Animal as pet where pet.name like :animalName"
                ,"animalName","%"+name+"%");
    }

    @Override
    public void close() {}
}
