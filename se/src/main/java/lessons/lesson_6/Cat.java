package lessons.lesson_6;

/**
 * Класс кошка
 */
public class Cat implements Pet{
    private final Animal animal;

    public Cat(String name) {
        this.animal = new Animal(name);
    }

    public Cat(Animal animal) {
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

        Cat cat = (Cat) obj;

        if(this.animal != null ? !this.animal.equals(cat.animal) : cat.animal != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
