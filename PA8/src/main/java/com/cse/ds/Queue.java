/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, Piazza, discussion
 */
package com.cse.ds;

import java.util.*;

/**
 * Implements a queue (linear data structure)
 */
public class Queue<E> {

    private MyQueueNode<E> head;
    private MyQueueNode<E> tail;
    private int nElements;

    /**
     * Initializes a MyQueue object, head and tail nodes initially null
     */
    public Queue() {
        this.head = null;
	this.tail = null;
	this.nElements = 0;
    }

    /**
     * Adds element of generic type E to end of queue
     *
     * @param element generic type element to add
     */
    public void enqueue(E element) throws NullPointerException {
        // null exception
	if (element == null) {
	    throw new NullPointerException();
	}
	
        MyQueueNode node = new MyQueueNode(element);
	// empty list
	if (this.head == null) {
	    this.head = node;
	    this.tail = node;
	}
	// more than one node
	else {
	    tail.setNext(node);
	    tail = tail.getNext();
	}
	this.nElements++;
    }

    /**
     * Removes and returns element generic type E from head of queue
     *
     * @return head of queue
     */
    public E dequeue() throws NoSuchElementException {
        // empty exception
	if (this.size() == 0) {
	    throw new NoSuchElementException();
	}

	E node = this.head.getElement();
	// 1 node
	if (this.size() == 1) {
	    head = null;
	    tail = null;
	}
	// 2 or more
	else {
	    head = head.getNext();
	}

	nElements--;
	return node;
    }

    /**
     * Returns element generic type E form head of queue
     *
     * @return head of queue
     */
    public E peek() throws NoSuchElementException {
        // empty exception
	if (this.size() == 0) {
	    throw new NoSuchElementException();
	}

	return this.head.getElement();
    }

    /**
     * Returns true if queue empty, else false
     *
     * @return whether queue is empty or not
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns number of elements in queue
     *
     * @return size of queue
     */
    public int size() {
        return nElements;
    }
    
}
