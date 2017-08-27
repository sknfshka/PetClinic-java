package lessons.lesson_6;

/**
 * Класс животное
 */
public class Animal implements Pet {
    /**
     * имя животного
     */
    private String name;
    private int age;

    public Animal(String name) {
            this.name = name;
            }

    public Animal(String name, int age) {
        this.name = name; this.age = age;
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
    @Override
    public String getName() {
            return this.name;
            }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить возраст животного
     * @return имя
     */
    public int getAge() {
        return age;
    }

}
