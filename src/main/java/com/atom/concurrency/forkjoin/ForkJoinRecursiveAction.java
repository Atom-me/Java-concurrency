package com.atom.concurrency.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Atom
 * @see ForkJoinPool 分而治之，把 一个任务分为若干个小的任务，分给多个线程去执行，然后一步一步的汇总过来。
 * @see java.util.concurrent.RecursiveAction 无返回值
 * @see java.util.concurrent.RecursiveTask  有返回值
 */
public class ForkJoinRecursiveAction {

    private final static int MAX_THRESHOLD = 3;

    private final static AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursiveAction(0, 10));
        // 等一会儿，让他计算完，我们在查看计算结果SUM
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        Optional.of(SUM).ifPresent(System.out::println);
    }

    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;

        public CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                final CalculateRecursiveAction leftAction = new CalculateRecursiveAction(start, middle);
                final CalculateRecursiveAction rightAction = new CalculateRecursiveAction(middle + 1, end);
                leftAction.fork();
                rightAction.fork();
            }
        }
    }
}
