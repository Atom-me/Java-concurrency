package com.atom.concurrency.synchronize;


/**
 * @author Atom
 * <p>
 * 银行
 */
public class BankVersion3 {
    public static void main(String[] args) {
        final SynchronizedRunnable ticketWindowRunnable = new SynchronizedRunnable();

        Thread t1 = new Thread(ticketWindowRunnable, "一号柜台");
        Thread t2 = new Thread(ticketWindowRunnable, "二号柜台");
        Thread t3 = new Thread(ticketWindowRunnable, "三号柜台");

        t1.start();
        t2.start();
        t3.start();

    }
}
