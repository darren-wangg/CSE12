/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza, StackOverflow, Geeksforgeeks
 */
package com.cse.ds.heaps;

import java.util.Arrays;

/**
 * Implements a generic heap data structure.
 */
public class Heap {
	
	// stores data in accoradance with heap rules.
        private Tuple[] data;
	// keeps track last index where element inserted.
        private int last_idx;

	private static final int HALF_DIVISOR = 2;
	
	/**
	 * Default no-arg constructor, initializes empty heap data structure
	 */
	public Heap() {
		this.data = new Tuple[1];
		this.last_idx = 0;
	}
	
	/**
	 * Create a heap out of an array of Tuples.
	 * Deep copy.
	 *
	 * @param arr Tuple array with priority and value
	 */
	public Heap(Tuple arr[]) {
		this.data = new Tuple[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			data[i + 1] = arr[i];
		}
		this.last_idx = data.length - 1;
		// Build-Max-Heap
		for (int i = (data.length - 1) / HALF_DIVISOR; i >= 1; i--) {
			heapify(i);
		}
	}
	
	/**
	 * This corrects a single violation at position i, if left and right 
	 * subtrees are max heaps.
	 *
	 * @param i index
	 */
	public void heapify(int i) {
		// index i out of bounds
		if (i > data.length - 1 || i < 1) {
			throw new IllegalArgumentException(
			"Cannot Heapify index greater than heap size.");
		}

		// violates heap property
		int left = HALF_DIVISOR * i;
		int right = (HALF_DIVISOR * i) + 1;
		int largest;
		if (left <= last_idx && data[left].priority >
		    data[i].priority) {
			largest = left;
		} else {
			largest = i;
		}
		if (right <= last_idx && data[right].priority >
		    data[largest].priority) {
			largest = right;
		}
		// swap largest to maintain heap property
		if (largest != i) {
			Tuple tmp = data[i];
			data[i] = data[largest];
			data[largest] = tmp;
			heapify(largest);
		}
	}
	
	/**
	 * This function returns the max element from the heap without
	 * removing the element from heap.
	 *
	 * @return max element from the heap
	 */
	public Tuple getMax() {
		// heap empty
		if (getSize() == 0) {
			return null;
		}
		return data[1];
	}
	
	/**
	 * This function removes and returns the max element from the heap
	 *
	 * @return max element from the heap
	 */
	public Tuple heappop() {
		// heap empty
		if (getSize() == 0) {
			return null;
		}
		// keep track max Tuple
		Tuple pop = data[1];
		data[1] = data[last_idx];
		// "replace deleted element with Tuple..."
		data[last_idx] = new Tuple(Integer.MIN_VALUE, null);
		heapify(1);
		last_idx--;
		return pop;
	}
	
	/**
	 * This function increases the priority of a node.
	 *
	 * @param i index
	 * @param priority new priority for the node
	 */
	public void increasePriority(int i, int priority) {
		// new priority not greater than old priority
		if (priority < data[i].priority) {
			throw new
			IllegalArgumentException("Priority less than Current");
		}
		Tuple previous = data[i];
		data[i] = new Tuple(priority, previous.value);
		// child node greater than parent node
		while (i > 1 && data[i / HALF_DIVISOR].priority < data[i].priority) {
			// swap child and parent node
			Tuple tmp = data[i];
			data[i] = data[i / HALF_DIVISOR];
			data[i / HALF_DIVISOR] = tmp;
			i = i / HALF_DIVISOR;
		}
	}
	
	/**
	 * This function pushes a new element in the heap
	 *
	 * @param arg Tuple to push
	 */
	public void heappush(Tuple arg) {
		// resize array
		Tuple[] newArray = new Tuple[data.length + 1];
		int i = 1;
		while (i < newArray.length - 1) {
			newArray[i] = data[i];
			i++;
		}
		// add new Tuple to end of array
		newArray[newArray.length - 1] = arg;
		data = newArray;
		last_idx++;
		// makes new Tuple climb to appropriate position, "push" action
		data[last_idx] = new Tuple(Integer.MIN_VALUE, arg.value);
		increasePriority(last_idx, arg.priority);
	}
	
	/**
	 * This function returns the size of the heap.
	 *
	 * @return heap size
	 */
	public int getSize()
	{
		return last_idx;
	}

	/**
	 * Override Object's toString method
	 *
	 * @return Heap as an array in string format
	 */
	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
