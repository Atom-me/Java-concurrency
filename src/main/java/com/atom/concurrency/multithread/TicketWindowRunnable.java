package com.atom.concurrency.multithread;

/**
 * @author Atom
 */
public class TicketWindowRunnable implements Runnable {

    private static final int MAX = 500;
    private int index = 1;

    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread().getName() + " 当前的号码是 " + index++);
        }
    }
}
