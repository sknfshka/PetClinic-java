package ru.achrom.models;

public class Animal {

    private int id;
    private String name;
    private int age;
    private String kind;
    private Client owner;

    @Override
    public String toString() {
        return "id: " + this.id + " name: " + this.name + " age: " +  this.age + " kind: " + this.kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Animal() {}

    public Animal(int id, String name, int age, String kind, Client owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.kind = kind;
        this.owner = owner;
    }

    public Animal(String name, int age, String kind, Client owner) {
        this.name = name;
        this.age = age;
        this.kind = kind;
        this.owner = owner;
    }

    public Animal(int id, String name, int age, String kind) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.kind = kind;
    }

    public Animal(String name, int age, String kind) {
        this.name = name;
        this.age = age;
        this.kind = kind;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Animal animal = (Animal) obj;

        if(this.name != null ? !this.name.equals(animal.name) : animal.name != null) {
            return false;
        }

        if(id != animal.id) {
            return false;
        }

        if(this.age != animal.age) {
            return false;
        }

        if(this.kind != null ? !this.kind.equals(animal.kind) : animal.kind != null) {
            return false;
        }

        if(this.owner != null ? !this.owner.equals(animal.owner) : animal.owner != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
