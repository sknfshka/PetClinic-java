package models;

public class Animal {

    private int id;
    private String name;
    private int age;
    private String kind;
    private int clientId;

    @Override
    public String toString() {
        return "id: " + this.id + " name: " + this.name + " age: " +  this.age + " kind: " + this.kind + " clientId: " + this.clientId;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Animal() {}

    public Animal(int id, String name, int age, String kind, int clientId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.kind = kind;
        this.clientId = clientId;
    }

    public Animal(String name, int age, String kind, int clientId) {
        this.name = name;
        this.age = age;
        this.kind = kind;
        this.clientId = clientId;
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

        if(this.clientId != animal.clientId) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
