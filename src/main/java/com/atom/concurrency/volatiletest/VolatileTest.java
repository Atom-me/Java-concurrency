package com.atom.concurrency.volatiletest;

/**
 * @author Atom
 * INIT_VALUE 不加 volatile 关键字，过个线程分别读取和修改这个共享数据的时候就会出现数据不一致的问题
 * 原因就是CPU的缓存，每次读取的数据都是从当前线程的工作缓存中的数据，而不是主内存中的数据。
 * 加上volatile关键字之后
 * 写内存的时候（写一个共享变量的时候） JVM就会把当前线程对应的工作缓存中的共享变量值刷新到主内存中。
 * 读内存的时候（读一个共享变量的时候） JVM就会把当前线程对应的工作缓存置为无效，当前线程直接从主内存中读取共享变量。
 */
public class VolatileTest {

    private volatile static int INIT_VALUE = 0;// 线程间的共享数据
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
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println(Thread.currentThread().getName() + " update the local value = " + ++INIT_VALUE);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "writer").start();


    }
}
