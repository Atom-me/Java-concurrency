package com.atom.concurrency.multithread;

/**
 * @author Atom
 * <p>
 * 银行
 */
public class BankVersion2 {
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();

        Thread t1 = new Thread(ticketWindowRunnable, "一号柜台");
        Thread t2 = new Thread(ticketWindowRunnable, "二号柜台");
        Thread t3 = new Thread(ticketWindowRunnable, "三号柜台");

        t1.start();
        t2.start();
        t3.start();

    }
}
