package ru.job4j.calculator;

/**
 * Класс для выполнения математических операций.
 * @author Anton Matsik
 * @since 21.10.2017
 */
public class Calculator {
    /** Переменная возвращаемого результата. */
    private double result;

    /**
     * Операция сложения двух чисел типа double.
     * @param first первое число
     * @param second второе число
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Операция вычитания двух чисел типа double.
     * @param first первое число
     * @param second второе число
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Операция умножения двух чисел типа double.
     * @param first первое число
     * @param second второе число
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Операция деления двух чисел типа double.
     * @param first первое число
     * @param second второе число
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Метод получения результата вычислений.
     * @return результат вычислений типа double
     */
    public double getResult() {
        return this.result;
    }
}