package com.atom.concurrency.join;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Atom
 * <p>
 * Thread.join 一直等到你执行结束，我当前线程才去运行。（当前线程等待子线程执行完在执行自己。）
 * join 只相对于你当前线程就是main线程。如果你起两个子线程，main线程会等待这俩子线程执行完成在执行自己，但是这俩子线程是并行的交互执行。
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 100).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });

        Thread t2 = new Thread(() -> {
            IntStream.range(1, 100).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });

     /*   t1.start();
        t1.join(); // t1 join之后 main线程阻塞，此时不会往下执行。

        System.out.println("t1 join ===================");
        t2.start();
        t2.join();*/

        t1.start();
        t2.start();
        t1.join(); // 调整代码顺序之后，(t1,t2 都启动之后，t1 在join 同样会阻塞下面代码执行（ t2.join()方法） 所以t1和t2是交替执行的)
        System.out.println("t1 join ===================");
        t2.join();

        Optional.of("All of tasks finish done .").ifPresent(System.out::println);
        IntStream.range(1, 100).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));

    }
}
