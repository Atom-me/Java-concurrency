package com.atom.concurrency.synchronize;

/**
 * @author Atom
 */
public class SynchronizedRunnable implements Runnable {

    private static final int MAX = 500;
    private int index = 1;

    /**
     * 同步方法的方式，三个柜台（线程），这500个号就会全让一个柜台（线程）处理了。
     * 同步方法的锁是 this，就是当前类的一个实例。
     */
  /*  public synchronized void run() {
        while (true) {
            if (index > MAX)
                break;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 当前的号码是 " + index++);
        }
    }*/

    /**
     * 我们可以改造一下，把同步方法里面的业务提出来到一个独立的方法里，然后在使用同步方法就会有多个线程进来，而不是所有的任务都让一个线程执行完了。
     */
    public void run() {
        while (true) {
            if (ticket()) {
                break;
            }
        }
    }

    private synchronized boolean ticket() {
        if (index > MAX) {
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " 的号码是：" + (index++));
        return false;
    }
}
