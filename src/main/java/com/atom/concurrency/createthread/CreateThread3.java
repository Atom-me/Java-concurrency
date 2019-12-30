package com.atom.concurrency.createthread;

/**
 * @author Atom
 */
public class CreateThread3 {
    private int i = 0;
    private byte[] bytes = new byte[200 * 1024];

    private static int counter = 0;

    // main线程由JVM创建
    public static void main(String[] args) {
        int j = 0;
        int[] arr = new int[1024];
        try {

            add(0);
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(counter);
        }

    }

    private static void add(int i) {
        ++counter;
        add(i + 1);
    }
}
