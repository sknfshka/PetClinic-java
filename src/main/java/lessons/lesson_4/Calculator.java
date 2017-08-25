package lessons.lesson_4;

import lessons.lesson_8.IllegalArgumentsCountException;

/**
 * Класс калькулятор
 */
public class Calculator {
    /**
     * Результат вычисления
     */
    private int result = 0;

    /**
     * Сложение
     * @param arguments аргументы
     * @throws IllegalArgumentsCountException если аргументов нет, выкидываем исключение
     */
    public void addition(int ... arguments) throws IllegalArgumentsCountException {
        if(arguments.length == 0) {
            throw new IllegalArgumentsCountException();
        }

        for(int arg : arguments) {
            this.result += arg;
        }
    }
    /**
     * Умножение
     * @param arguments аргументы
     * @throws IllegalArgumentsCountException если аргументов нет, выкидываем исключение
     */
    public void multiplication(int ... arguments) throws IllegalArgumentsCountException {
        if(arguments.length == 0) {
            throw new IllegalArgumentsCountException();
        }

        this.result = this.result == 0 ? 1 : this.result;
        for(int arg : arguments) {
            this.result *= arg;
        }
    }
    /**
     * Деление
     * @param arguments аргументы
     * @throws IllegalArgumentsCountException если аргументов нет, выкидываем исключение
     */
    public void division(int ... arguments) throws IllegalArgumentsCountException {
        if(arguments.length == 0) {
            throw new IllegalArgumentsCountException();
        }

        this.result = this.result == 0 ? arguments[0] : this.result;
        for(int i = 1; i < arguments.length; i++) {
            if(arguments[i] == 0) {
                throw new IllegalArgumentException("Division by 0.");
            }

            this.result /= arguments[i];
        }
    }
    /**
     * Вычетание
     * @param arguments аргументы
     * @throws IllegalArgumentsCountException если аргументов нет, выкидываем исключение
     */
    public void substraction(int ... arguments) throws IllegalArgumentsCountException {
        if(arguments.length == 0) {
            throw new IllegalArgumentsCountException();
        }

        this.result = this.result == 0 ? arguments[0] : this.result;
        for(int i = 1; i < arguments.length; i++) {
            this.result -= arguments[i];
        }
    }
    /**
     * Получить результат вычислений
     * @return результат вычислений
     */
    public int getResult() {
        return result;
    }
    /**
     * Обнулить результат вычислений
     */
    public void eraseResult() {
        this.result = 0;
    }
}
