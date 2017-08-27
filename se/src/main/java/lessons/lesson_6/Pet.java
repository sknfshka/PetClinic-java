package lessons.lesson_6;

/**
 * Интерфейс питомец
 */
public interface Pet {
    /**
     * Получить имя
     * @return имя
     */
    String getName();
    void setName(String name);
    int getAge();
    String getKind();
    String getId();
    void setId(String id);
    void setAge(int age);
}
