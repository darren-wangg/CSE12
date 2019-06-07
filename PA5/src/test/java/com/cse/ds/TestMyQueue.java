package com.cse.ds;

import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class TestMyQueue {

    static MyQueue<Integer> intQueue = null;

    @Before // original
    public void testEnqueue() {
        intQueue = new MyQueue();
    }

    @Test // original
    public void testSize() {
        Assert.assertTrue(intQueue.size() == 0);
    }

    @Test // original
    public void testEnqueueIsEmpty() {
        intQueue.enqueue(1);
        Assert.assertFalse(intQueue.isEmpty());
    }

    @Test
    public void testEnqueueMultiple() {
        intQueue.enqueue(0);
	intQueue.enqueue(1);
	Assert.assertEquals(intQueue.size(),2);
    }

    @Test
    public void testDequeue() {
        intQueue.enqueue(1);
	intQueue.enqueue(2);
	int removed = intQueue.dequeue();
	Assert.assertEquals(removed,1);
	Assert.assertEquals(intQueue.size(),1);
    }

    @Test
    public void testPeek() {
        intQueue.enqueue(1);
	Assert.assertEquals((int) intQueue.peek(),1);
    }
    
}
