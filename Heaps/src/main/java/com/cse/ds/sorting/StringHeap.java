/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza, StackOverflow, Geeksforgeeks
 */
package com.cse.ds.sorting;

import java.util.Arrays;

/**
 * Implements a Heap for Strings.
 */
public class StringHeap {
	
	private String[] data;
	private int last_idx;

	private static final int HALF_DIVISOR = 2;
	
	/**
	 * Default no-arg constructor, initialzes empty heap data structure
	 */
	public StringHeap() {
		this.data = new String[1];
		this.last_idx = 0;
	}
	
	/**
	 * Create a heap out of an array of Strings.
	 *
	 * @param arr String array
	 */
	public StringHeap(String arr[]) {
		this.data = new String[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			data[i + 1] = arr[i];
		}
		this.last_idx = arr.length;
		// Build-Max-Heap
		for (int i = arr.length / HALF_DIVISOR; i >= 1; i--) {
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
		int left = 2 * i;
		int right = (2 * i) + 1;
		int largest;
		if (left <= last_idx && data[left].compareTo(data[i]) > 0) {
			largest = left;
		} else {
			largest = i;
		}
		if (right <= last_idx && data[right].compareTo(data[largest])
		    > 0) {
			largest = right;
		}
		if (largest != i) {
			String tmp = data[i];
			data[i] = data[largest];
			data[largest] = tmp;
			heapify(largest);
		}
	}
	
	/**
	 * This function returns the max element from the heap without removing 
	 * the element from heap.
	 *
	 * @return max element from the heap
	 */
	public String getMax() {
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
	public String heappop() {
		// heap empty
		if (getSize() == 0) {
			return null;
		}
		// keep track max Tuple
		String pop = data[1];
		data[1] = data[last_idx];
		// "replace deleted element with Tuple..."
		data[last_idx] = new String("");
		heapify(1);
		last_idx--;
		return pop;
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
	 * Overrides Object's toString method
	 *
	 * @return StringHeap as an array in string format
	 */
	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
