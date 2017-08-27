package lessons.lesson_3;

/**
 * Класс для вычисления суммы двух чисел. Числа беруться из входщего массива.
 */
public class Calculate {
    public static void main(String[] arg) {
        System.out.println("Calculate...");
        double first = Double.valueOf(arg[0]);
        double second = Double.valueOf(arg[1]);
        System.out.println("Sum : " + (first + second));
        System.out.println("Substr : " + (first - second));
        System.out.println("Div : " + (first / second));
        System.out.println("Mult : " + (first * second));
    }
}