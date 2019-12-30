package com.atom.concurrency.multithread;

/**
 * @author Atom
 * 税务计算器
 */
public class TaxCalculator {

    private final double salary;
    private final double bonus;

    private final CalculatoStrategy calculatoStrategy;

    public TaxCalculator(double salary, double bonus,CalculatoStrategy calculatoStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatoStrategy = calculatoStrategy;
    }


    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    protected double calcTax() {
        return calculatoStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return this.calcTax();
    }
}
