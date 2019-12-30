package com.atom.concurrency.waitandsleep;

import java.util.stream.Stream;

/**
 * @author Atom
 */
public class DifferenceOfWaitAndSleep {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {

//        m1();
//        m2();

        Stream.of("T1", "T2").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
//                    m1();
                    m2();
                }
            }.start();
        });
    }

    public static void m1() {
        synchronized (MONITOR) {
            try {
                System.out.println("the thread " + Thread.currentThread().getName() + " enter.");
                Thread.sleep(2000);// sleep 不会自动释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void m2() {
        /**
         *  wait 方法必须使用 同步,如果不使用 同步代码块(使用monitor，) 单独使用wait会抛异常IllegalMonitorStateException。
         * 如果不使用 同步代码块(使用monitor，) 使用在方法上使用 synchronized也不行，会抛异常
         * wait 就是必须在同步代码块中使用，必须等待其他人唤醒她。否则一直处于blocked状态，放弃CPU执行权。
         */
        synchronized (MONITOR) {
            try {
                System.out.println("the thread " + Thread.currentThread().getName() + " enter.");
                MONITOR.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
