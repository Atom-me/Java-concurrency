package com.atom.concurrency.multithread;

/**
 * @author Atom
 */
public class SimpleCalculatoStrategy implements CalculatoStrategy {

    private static final double SALARY_RATE = 0.1d;
    private static final double BONUS_RATE = 0.1d;

    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
