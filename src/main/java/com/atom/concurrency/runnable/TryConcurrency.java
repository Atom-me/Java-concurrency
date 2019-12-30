package com.atom.concurrency.runnable;

/**
 * @author Atom
 */
public class TryConcurrency {

    public static void main(String[] args) {


        Thread t1 = new Thread("READ-Thread") {
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                readFromDB();
            }
        };

        t1.run(); //  也可以直接调用run方法，但是并不会启动一个线程。

        new Thread("WRITE-Thread") {
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }


    private static void readFromDB() {
        //read data from database and handle it
        try {
            println("begin read data from db.");
            Thread.sleep(1000 * 10L);
            println("read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void writeDataToFile() {
        try {
            println("begin write data to File.");
            Thread.sleep(1000 * 10L);
            println("write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }


    private static void println(String message) {
        System.out.println(message);

    }
}
