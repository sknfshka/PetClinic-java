package lessons.lesson_4;

import lessons.lesson_8.IllegalArgumentsCountException;

import java.util.Scanner;

/**
 * Класс вычисления из консоли
 */
public class InteractRunner {
    public static void main(String[] args) throws IllegalArgumentException{
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        String exit = "no";

        try {
            while(!exit.equals("yes")) {
                System.out.println("Enter first argument.");
                int firstArgument = Integer.valueOf(scanner.next());
                System.out.println("Enter second argument.");
                int secondArgument = Integer.valueOf(scanner.next());
                System.out.println("Choose operation. (+/*///-)");
                String typeOfOperation = scanner.next();

                switch (typeOfOperation) {
                    case "+":
                        calculator.addition(firstArgument, secondArgument);
                        break;
                    case "*":
                        calculator.multiplication(firstArgument, secondArgument);
                        break;
                    case "/":
                        calculator.division(firstArgument, secondArgument);
                        break;
                    case "-":
                        calculator.substraction(firstArgument, secondArgument);
                        break;
                    default:
                        throw new IllegalArgumentException("Operation type");
                }

                System.out.println(calculator.getResult());
                System.out.println("Exit? (yes/no)");
                exit = scanner.next();

                if(exit.equals("no")) {
                    System.out.println("Save result for next operations? (yes/no)");

                    if(scanner.next().equals("no"))
                        calculator.eraseResult();
                }
            }
        }
        catch (IllegalArgumentsCountException e) {
            System.out.println(e.getMessage());
        }
        finally {
            scanner.close();
        }

    }
}
