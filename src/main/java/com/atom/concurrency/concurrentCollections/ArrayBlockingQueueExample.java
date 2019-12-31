package com.atom.concurrency.concurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Atom
 * @see ArrayBlockingQueue
 * This queue orders elements FIFO (first-in-first-out)
 * Once created, the capacity cannot be changed.
 *
 */
public class ArrayBlockingQueueExample {

    public <T> ArrayBlockingQueue<T> create(int size) {
        return new ArrayBlockingQueue<>(size);
    }

    
}
