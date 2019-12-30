package com.atom.concurrency.producerandconsumer;

import java.util.stream.Stream;

/**
 * @author Atom
 * <p>
 * 在多个生产者和多个消费者的情况下 使用while 不要使用if ，使用notifyAll
 */
public class ProduceConsumerVersion3 {

    private int i = 1;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    private void produce() {
        synchronized (LOCK) {
            while (isProduced) { // 如果你已经生产了，就别在生产了，等着其他线程去消费。消费完在生产。
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P->" + i);
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    private void consume() {
        synchronized (LOCK) {
            while (isProduced) { // 如果已经生产了，我这里就去消费他。消费完把生产者唤醒（notify）。
                System.out.println("C->" + i);
                LOCK.notifyAll();
                isProduced = false; //消费完，把是否已经生产置为false，让生产者接着去生产。
            }
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();

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
