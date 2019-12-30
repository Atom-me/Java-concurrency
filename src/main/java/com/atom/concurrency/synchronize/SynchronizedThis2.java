package com.atom.concurrency.synchronize;

/**
 * @author Atom
 * <p>
 * m1 m2这两个方法 也可以使用同步代码块，使用同一个锁（显式指定一个对象锁 Lock）,这也是互斥的。
 */
public class SynchronizedThis2 {

    public static void main(String[] args) {

        ThisLock2 thisLock = new ThisLock2();

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

class ThisLock2 {

    private Object Lock = new Object(); //  定一个锁

    public void m1() {
        synchronized (Lock) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void m2() {
        synchronized (Lock) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

