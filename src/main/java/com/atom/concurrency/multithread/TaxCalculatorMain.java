package com.atom.concurrency.multithread;

/**
 * @author Atom
 */
public class TaxCalculatorMain {

    public static void main(String[] args) {
        /*final TaxCalculator calculator = new TaxCalculator(10000d, 2000d) {

            @Override
            public double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = calculator.calculate();
        System.out.println(tax);

         */

        TaxCalculator calculator = new TaxCalculator(10000d, 2000d, (s, b) -> s * 0.1 + b * 0.15);
//        CalculatoStrategy strategy = new SimpleCalculatoStrategy();
//        calculator.setCalculatoStrategy(strategy);
//        calculator.setCalculatoStrategy((s, b) -> s * 0.1 + b * 0.15);
        System.out.println(calculator.calculate());
    }
}
