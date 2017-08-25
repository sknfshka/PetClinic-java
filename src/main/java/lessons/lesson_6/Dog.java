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
    /**
     * Получить имя животного
     * @return имя
     */
    public String getName() {
        return this.animal.getName();
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
