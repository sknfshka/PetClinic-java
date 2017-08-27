package lessons.lesson_6;

/**
 * Created by User on 25.08.2017.
 */
public class ClinicRunner {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        clinic.add(new Client("Brown", new Dog("Digy")));
        clinic.add(new Client("Nick", new Dog("Sparky")));
        clinic.add(new Client("Brown", new Dog("Digy")));
        clinic.add(new Client("Brown", new Cat("Flaffy")));

        System.out.print(clinic);
    }
}
