package com.atom.concurrency.synchronize;

/**
 * @author Atom
 * <p>
 * <p>
 */
public class HelloSynchronized extends Thread {
    public int count;

    public void exec() {
        count += 1;
    }

    @Override
    public void run() {
        synchronized (this) {
            exec();
        }
    }

    public static void main(String[] args) {
        new HelloSynchronized().start();
    }
}
