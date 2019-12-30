package com.atom.concurrency.deadlock;

/**
 * @author Atom
 * <p>
 * jstack 进程ID 可以查看到发生了死锁
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);


        new Thread() {
            @Override
            public void run() {
                while (true) {
                    deadLock.m1();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    otherService.s2();
                }
            }
        }.start();

    }
}
