package models;

import java.util.LinkedList;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private List<Animal> animals = new LinkedList<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(int id) {
        this.id = id;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String resultString = "id: " + this.id + " name: " + this.name + " animals: { ";

        for (Animal animal : this.animals) {
            resultString = resultString + animal.toString() + "; ";
        }

        resultString = resultString + "}";

        return resultString;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Client client = (Client) obj;

        if(this.name != null ? !this.name.equals(client.name) : client.name != null) {
            return false;
        }

        return true;
    }

    public void addAnimal(Animal animal) {
        if (animal == null)
            return;
        animals.add(animal);
    }
}
