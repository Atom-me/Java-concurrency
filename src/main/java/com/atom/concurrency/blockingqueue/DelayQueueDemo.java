package com.atom.concurrency.blockingqueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * 模拟网吧上网，定时下机操作
 *
 * @author Atom
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Customer> delayQueue = new DelayQueue<>();
        // 放入三个上网用户，上机时长分别为 5秒，10秒，20秒
        Customer customer1 = new Customer(1001, "阿童木", 1000 * 5);
        Customer customer2 = new Customer(1002, "旺仔", 1000 * 10);
        Customer customer3 = new Customer(1001, "歪歪", 1000 * 20);
        delayQueue.add(customer1);
        delayQueue.add(customer2);
        delayQueue.add(customer3);
        //监控下机的线程（调用queue.take()阻塞方法，到时间才能拿到数据）
        DownTask downTask = new DownTask(delayQueue);
        new Thread(downTask).start();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        while (true) {
            Thread.sleep(1000);
            System.out.println(sdf.format(new Date()));
        }

    }
}
