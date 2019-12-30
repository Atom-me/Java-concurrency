package com.atom.concurrency.synchronize;

/**
 * @author Atom
 */
public class TicketWindowRunnable implements Runnable {

    private static final int MAX = 500;
    private int index = 1;

    private final Object MONITOR = new Object();

    public void run() {
        while (true) {
            synchronized (MONITOR) {
                if (index > MAX)
                    break;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 当前的号码是 " + index++);
            }
        }
    }
}
