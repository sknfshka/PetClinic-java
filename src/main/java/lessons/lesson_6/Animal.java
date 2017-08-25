package lessons.lesson_6;

/**
 * Класс животное
 */
public class Animal implements Pet {
    /**
     * имя животного
     */
    private final String name;

    public Animal(final String name) {
            this.name = name;
            }

    @Override
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

        return true;
    }
    /**
     * Получить имя животного
     * @return имя
     */
    public String getName() {
            return this.name;
            }
}
