package com.atom.concurrency.synchronize;

/**
 * @author Atom
 *
 * m1 m2这两个方法都是synchronized做的同步方法，同步方法在非静态方法上就是对象锁（this锁）.
 * 所以t1 和t2 两个线程只有一个线程可以拿到锁，第一个线程拿到锁之后另一个线程就需要等待第一个线程释放锁之后才能拿到这个锁，因为这是一把锁。
 */
public class SynchronizedThis {

    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();

        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

