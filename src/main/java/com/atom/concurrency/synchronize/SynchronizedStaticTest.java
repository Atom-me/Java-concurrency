package com.atom.concurrency.synchronize;

/**
 * @author Atom
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        new Thread("T1") {
            @Override
            public void run() {
                Synchronizedstatic.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                Synchronizedstatic.m2();
            }
        }.start();

        new Thread("T3") {
            @Override
            public void run() {
                Synchronizedstatic.m3();
            }
        }.start();
    }
}
