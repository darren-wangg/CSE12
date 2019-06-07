/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza
 */
package com.cse.ds.heaps;

import com.cse.ds.heaps.Heap;
import com.cse.ds.heaps.Tuple;

/**
 * Implements HeapSort
 * 
 * @param <E> Generic type E
 */
public class HeapSortGeneric<E> {
	
	private E[] data;
	private int[] priority;

	private Tuple[] array;

	private static final int HALF_DIVISOR = 2;
	
	/**
	 * Initializes heap sort algorithm creating a Tuple array with priority
	 * and value data
	 *
	 * @param data Tuple value
	 * @param priority Tuple priority
	 */
	public HeapSortGeneric(E[] data, int[] priority) {
		this.data = data;
		this.priority = priority;
		this.array = new Tuple[data.length];
		// create heap
		for (int i = 0; i < data.length; i++) {
			array[i] = new Tuple(priority[i], data[i]);
		}
	}
	
	/**
	 * This method sorts the value and returns a new array
	 *
	 * @param ascending controls order of sort
	 * @return sorted array
	 */
	public E[] sort(boolean ascending) {
		// build the max heap
		Heap heap = new Heap(array);
		E[] sorted = (E[]) new Object[array.length];
		for (int i = 0; i < array.length; i++) {
			sorted[i] = (E) heap.heappop().value;
		}

		// if ascending, reverse the array
		if (ascending) {
			for (int i = 0; i < data.length / HALF_DIVISOR; i++) {
				E tmp = sorted[i];
				sorted[i] = sorted[sorted.length - i - 1];
				sorted[sorted.length - i - 1] = tmp;
			}
		}
		return sorted;
	}
}
