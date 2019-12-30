package com.atom.concurrency.synchronize;

/**
 * @author Atom
 * <p>
 * m1 和m2 使用的是同一把锁，类锁。
 * <p>
 * m3静态方法 没有加锁，但是会等待静态代码块 执行完在执行，在静态代码块里面有类锁，所以会等待。
 */
public class Synchronizedstatic {

    // 静态代码块里面我们 monitor的是 这个类的class
    static {
        synchronized (Synchronizedstatic.class) {
            try {
                System.out.println("static block synchronized " + Thread.currentThread().getName());
                Thread.currentThread().sleep(10_000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        System.out.println("m3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
