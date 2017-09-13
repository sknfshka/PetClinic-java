package ru.achrom.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Storages {
    public final JdbcClientStorage clientStorage;
    public final JdbcAnimalStorage animalStorage;

    @Autowired
    public Storages(JdbcClientStorage clientStorage, JdbcAnimalStorage animalStorage) {
        this.clientStorage = clientStorage;
        this.animalStorage = animalStorage;
    }
}
