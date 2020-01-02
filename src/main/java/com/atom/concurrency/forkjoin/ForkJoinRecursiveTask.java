package com.atom.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author Atom
 * @see ForkJoinPool 分而治之
 * @see java.util.concurrent.RecursiveAction 无返回值
 * @see java.util.concurrent.RecursiveTask  有返回值
 */
public class ForkJoinRecursiveTask {

    /**
     * 定义一个最大的阈值，单个任务处理到最大阈值。
     */
    private final static int MAX_THRESHOLD = 200;

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        final ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculatedRecursiveTask(0, 1000));
        try {
            final Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算
     */
    private static class CalculatedRecursiveTask extends RecursiveTask<Integer> {
        /**
         * 从哪开始计算
         */
        private final int start;
        /**
         * 到哪里结束
         */
        private final int end;

        public CalculatedRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            // 开始和结束之间到数据没有超过定义到最大阈值，可以直接计算，不需要拆分。
            if (end - start <= MAX_THRESHOLD) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                // 拆分为两个任务
                int middle = (start + end) / 2;
                final CalculatedRecursiveTask leftTask = new CalculatedRecursiveTask(start, middle);
                final CalculatedRecursiveTask rightTask = new CalculatedRecursiveTask(middle + 1, end);

                leftTask.fork();
                rightTask.fork();

                return leftTask.join() + rightTask.join();
            }
        }
    }
}
