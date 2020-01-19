package com.atom.concurrency.blockingqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 支持优先级的阻塞队列
 * 直接遍历队列元素是无序的
 * 一个一个的获取 poll take 元素是有优先级顺序的
 *
 * @author Atom
 */
public class PriorityBlockingQueueTest {


    @Test
    public void testSeq() throws InterruptedException {
        BlockingQueue<Task> blockingQueue = new PriorityBlockingQueue<>(16);
        blockingQueue.put(new Task(9, "task9"));
        blockingQueue.put(new Task(1, "task1"));
        blockingQueue.put(new Task(3, "task3"));
        blockingQueue.put(new Task(3, "task3——1"));
        blockingQueue.put(new Task(6, "task6"));

        // 直接遍历队列元素是无序的
        for (Task task : blockingQueue) {
            System.out.println(task);
        }
        System.out.println("=================");
        // 一个一个的获取 poll take 元素是有优先级顺序的
        for (; !blockingQueue.isEmpty(); ) {
            System.out.println(blockingQueue.poll());
        }
    }


    /**
     * drainTo 方法会以优先级顺序将队列里的元素转移到另一个集合里
     *
     * @throws InterruptedException
     */
    @Test
    public void testDrainTo() throws InterruptedException {
        BlockingQueue<Task> blockingQueue = new PriorityBlockingQueue<>(16);
        blockingQueue.put(new Task(9, "task9"));
        blockingQueue.put(new Task(1, "task1"));
        blockingQueue.put(new Task(3, "task3"));
        blockingQueue.put(new Task(3, "task3——1"));
        blockingQueue.put(new Task(6, "task6"));

        List<Task> taskList = new ArrayList<>();
        blockingQueue.drainTo(taskList, 3);
        System.out.println(taskList);
    }
}
