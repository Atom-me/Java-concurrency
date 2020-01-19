package com.atom.concurrency.blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ArrayBlockingQueueExampleTest {

    private ArrayBlockingQueueExample example;

    @Before
    public void setUp() {
        example = new ArrayBlockingQueueExample();
    }

    @After
    public void tearDown() {
        example = null;
    }

    @Test
    public void testAddMethod() {
        final ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.add("hello1"), equalTo(true));
        assertThat(queue.add("hello2"), equalTo(true));
        assertThat(queue.add("hello3"), equalTo(true));
        assertThat(queue.add("hello4"), equalTo(true));
        assertThat(queue.add("hello5"), equalTo(true));
        assertThat(queue.size(), equalTo(5));
    }

    /**
     * add 方法超出容量，会抛异常 IllegalStateException
     */
    @Test(expected = IllegalStateException.class)
    public void testAddMethodExceedCapacity() {
        final ArrayBlockingQueue<String> queue = example.create(5);
        assertThat(queue.add("hello1"), equalTo(true));
        assertThat(queue.add("hello2"), equalTo(true));
        assertThat(queue.add("hello3"), equalTo(true));
        assertThat(queue.add("hello4"), equalTo(true));
        assertThat(queue.add("hello5"), equalTo(true));
        assertThat(queue.add("hello6"), equalTo(true));// 容量为5 ，第六个插入失败
        fail("shout not process to here");
    }

}