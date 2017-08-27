package lessons.lesson_6;

/**
 * Класс собака
 */
public class Dog implements Pet {
    private final Animal animal;

    public Dog(String name) {
        this.animal = new Animal(name);
    }

    public Dog(Animal animal) {
        this.animal = animal;
    }

    public String getName() {
        return this.animal.getName();
    }

    public void setName(String name) {
        this.animal.setName(name);
    }

    @Override
    public int getAge() {
        return animal.getAge();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Dog dog = (Dog) obj;

        if(this.animal != null ? !this.animal.equals(dog.animal) : dog.animal != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
