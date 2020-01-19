package com.atom.concurrency.blockingqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Atom
 */
public class Customer implements Delayed {

    private int id;
    private String name;
    private long endTime;

    public Customer(int id, String name, long endTime) {
        this.id = id;
        this.name = name;
        // 加上当前时间
        this.endTime = endTime + System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.endTime - ((Customer) o).endTime);
    }
}
