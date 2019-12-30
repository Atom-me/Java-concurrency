package com.atom.concurrency.closethread;

/**
 * @author Atom
 */
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            // load a very heavy resource.
           /* while (true) {

            }*/
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000L);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
