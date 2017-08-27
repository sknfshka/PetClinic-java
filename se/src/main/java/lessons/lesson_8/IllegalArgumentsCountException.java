package lessons.lesson_8;

/**
 * Created by User on 25.08.2017.
 */
public class IllegalArgumentsCountException extends Exception{
    public IllegalArgumentsCountException() {
        super("Wrong arguments number.");
    }
}
