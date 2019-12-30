package com.atom.concurrency.createthread;

/**
 * @author Atom
 */
public class CreateThread {

    public static void main(String[] args) {
        Thread t = new Thread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("run ......");
            }
        };
        t.start();
        t2.start();
        System.out.println(t.getName());
        System.out.println(t2.getName());

        Thread t3 = new Thread(" myThreadname");
        System.out.println(t3.getName());
        Thread t4 = new Thread();
        System.out.println(t4.getName());

    }
}
