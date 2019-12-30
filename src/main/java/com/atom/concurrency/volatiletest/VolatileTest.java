package com.atom.concurrency.volatiletest;

/**
 * @author Atom
 */
public class VolatileTest {

    private /*volatile*/ static int INIT_VALUE = 0;// 线程间的共享数据
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.println(Thread.currentThread().getName() + " read the local value = " + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "reader").start();


        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                System.out.println(Thread.currentThread().getName() + " update the local value = " + ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "writer").start();


    }
}
