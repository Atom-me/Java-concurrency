package com.atom.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 监控网吧机器下机任务
 *
 * @author Atom
 */
public class DownTask implements Runnable {
    private BlockingQueue queue;

    public DownTask(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Customer customer = (Customer) this.queue.take();
                System.out.println("编号： " + customer.getId() + " 姓名：" + customer.getName() + " 下机");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
