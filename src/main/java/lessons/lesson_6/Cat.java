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
