package lessons.lesson_6;

/**
 * Created by User on 26.08.2017.
 */
public class WrongInputDataException extends Exception {
    public WrongInputDataException() {
        super("Wrong input data. There are no such clients.");
    }
}
