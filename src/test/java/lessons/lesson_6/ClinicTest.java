package lessons.lesson_6;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 25.08.2017.
 */
public class ClinicTest {
    @Test
    public void addClient() throws Exception {
        Clinic clinic = new Clinic();
        clinic.addClient(new Client("Mike", new Dog("Digy")));
        clinic.addClient(new Client("Nick", new Dog("Sparky")));
        clinic.addClient(new Client("Mike", new Dog("Digy")));
        clinic.addClient(new Client("Brown", new Cat("Flaffy")));
        assertEquals(3, clinic.countOfClients());
    }

    @Test (expected = WrongInputDataException.class)
    public void findClientsException() throws WrongInputDataException {
        Clinic clinic = new Clinic();
        clinic.addClient(new Client("Mike", new Dog("Digy")));
        clinic.findClientsByClientName("Nick");
    }

}