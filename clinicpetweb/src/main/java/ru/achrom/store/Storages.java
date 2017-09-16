package ru.achrom.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Storages {
    public final IHTClientStorage clientStorage;
    public final IHTAnimalStorage animalStorage;

    @Autowired
    public Storages(IHTClientStorage hibernateTemplateClientStorage, IHTAnimalStorage hibernateTemplateAnimalStorage) {
        this.clientStorage = hibernateTemplateClientStorage;
        this.animalStorage = hibernateTemplateAnimalStorage;
    }
}
