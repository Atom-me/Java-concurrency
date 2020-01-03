package com.atom.concurrency.synchronize;

/**
 * @author Atom
 * <p>
 * synchronized 加在静态方法上面，我们在反编译的字节码上是无法看到monitorenter 和 monitorexit 指令的，事实上它是锁类整个类，就是class。使用的字节码文件做为的锁。
 * <p>
 */
public class HelloSynchronizedStaticMethod {
    public static int count;

    public static synchronized void exec() {
        count += 1;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                exec();
            });
            thread.start();
        }
        System.out.println(count);
    }
}
