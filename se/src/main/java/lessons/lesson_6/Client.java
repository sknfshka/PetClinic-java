package lessons.lesson_6;

/**
 * Класс клиент клиники
 */
public class Client {
    /**
     * Идентификатор клиента
     */
    private int id;
    /**
     * Имя клиента
     */
    private String name;
    /**
     * Питомец клиента
     */
    private Pet pet;

    /**
     * Конструктор
     * @param id идентификатор
     * @param name имя
     */
    public Client(int id, String name) {
        this.name = name;
        this.id = id;
    }
    /**
     * Конструктор
     * @param name имя
     * @param pet питомец
     */
    public Client(String name, Pet pet) {
        this.name = name;
        this.pet = pet;
    }

    /**
     * Конструктор
     * @param id идентификатор
     * @param name имя
     * @param pet питомец
     */
    public Client(int id, String name, Pet pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
    }

    public Client() {
    }

    /**
     * Копирование клиента
     * @param client клиент
     */
    public void copyFromClient(Client client) {
        this.id = client.id;
        this.name = client.name;
        this.pet = client.pet;
    }
    /**
     * Узнать питомца клиента
     * @return питомец клиента
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Указать питомца клиенту
     * @param pet питомец
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Узнать идентификатор клиента
     * @return идентификатор клиента
     */
    public int getId() {
        return id;
    }

    /**
     * Указать идентификатор клиента
     * @param id идентификатор клиента
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Узнать имя клиента
     * @return имя клиента
     */
    public String getName() {
        return name;
    }

    /**
     * Указать имя клиенту
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Узнать кличку клиента
     * @return кличка клиента
     */
    public String getPetName() {
        return this.pet == null ? "" : this.pet.getName();
    }

    @Override
    public String toString() {
        String petName = this.pet == null ? "" : this.pet.getName();
        return this.id + " " + this.name + " - " + petName;
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

        if(this.pet != null ? !this.pet.equals(client.pet) : client.pet != null) {
            return false;
        }

        return true;
    }

    public void setPetName(String petName) {
        if(pet == null)
            return;

        pet.setName(petName);
    }

    public String getPetKind() {
        return pet == null ? "" : pet.getKind();
    }
}
