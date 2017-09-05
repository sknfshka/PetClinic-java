package models;

public class WrongInputDataException extends Exception {
    public WrongInputDataException() {
        super("Wrong input data. There are no such clients.");
    }
}
