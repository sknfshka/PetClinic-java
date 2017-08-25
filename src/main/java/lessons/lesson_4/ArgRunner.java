package lessons.lesson_4;

import lessons.lesson_8.IllegalArgumentsCountException;

/**
 * Класс вычисления из аргументов
 */
public class ArgRunner {
    public static void main(String[] args) {
        System.out.println("Calculate...");
        try {
            int first = Integer.valueOf(args[0]);
            int second = Integer.valueOf(args[1]);
            Calculator calc = new Calculator();
            calc.addition(first, second);
            System.out.println("Sum : " + calc.getResult());
        }
        catch (IllegalArgumentsCountException e) {
            System.out.println(e.getMessage());
        }

    }
}
