package com.atom.concurrency.join;

import org.junit.Test;

/**
 * @author Atom
 */
public class ThreadJoinDemo {

    public static int count;

    public static void exec() {
        count += 1;
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "开始执行。。。count=" + count);
            Thread.sleep(10000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                exec();
            });
            thread.start();
            thread.join();
            System.out.println("主线程开始执行" + Thread.currentThread().getName());
        }

        System.out.println(count);
    }
}
