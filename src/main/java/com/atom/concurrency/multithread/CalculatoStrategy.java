package com.atom.concurrency.multithread;

/**
 * @author Atom
 */
@FunctionalInterface
public interface CalculatoStrategy {
    double calculate(double salary, double bonus);
}
