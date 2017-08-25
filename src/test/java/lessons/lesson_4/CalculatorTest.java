package lessons.lesson_4;

import lessons.lesson_8.IllegalArgumentsCountException;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void addition() throws IllegalArgumentsCountException {
        Calculator calculator = new Calculator();
        calculator.addition(1, 2);
        assertEquals(3, calculator.getResult());
    }

    @Test (expected = IllegalArgumentsCountException.class)
    public void additionUserException() throws IllegalArgumentsCountException {
        Calculator calculator = new Calculator();
        calculator.addition();
    }

    @Test
    public void multiplication() throws Exception {
        Calculator calculator = new Calculator();
        calculator.multiplication(3, 2);
        assertEquals(6, calculator.getResult());

    }

    @Test
    public void eraseResult() throws Exception {
        Calculator calculator = new Calculator();
        calculator.eraseResult();
        assertEquals(0, calculator.getResult());
    }

}