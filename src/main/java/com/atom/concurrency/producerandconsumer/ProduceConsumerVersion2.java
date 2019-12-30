package com.atom.concurrency.producerandconsumer;

import java.util.stream.Stream;

/**
 * @author Atom
 * <p>
 * 在多个生产者和多个消费者的情况下是有问题的
 */
public class ProduceConsumerVersion2 {

    private int i = 1;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce() {
        synchronized (LOCK) {
            if (isProduced) { // 如果你已经生产了，就别在生产了，等着其他线程去消费。消费完在生产。
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    private void consume() {
        synchronized (LOCK) {
            if (isProduced) { // 如果已经生产了，我这里就去消费他。消费完把生产者唤醒（notify）。
                System.out.println("C->" + i);
                LOCK.notify();
                isProduced = false; //消费完，把是否已经生产置为false，让生产者接着去生产。
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();

        /*
         //单个生产者和单个消费者 没有问题
        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();*/


        // 多个生产者和多个消费者 存在问题
        Stream.of("p1", "p2").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                    }
                }
            }.start();
        });

        Stream.of("c1", "c2").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
                    while (true) {
                        pc.consume();
                    }
                }
            }.start();
        });

    }
}
