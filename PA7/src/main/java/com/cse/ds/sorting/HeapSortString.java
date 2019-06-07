/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza
 */
package com.cse.ds.sorting;

/**
 * Implements HeapSort for Strings using interface Sorting
 */
public class HeapSortString implements Sorting<String> {
	
	private static final int HALF_DIVISOR = 2;

	/**
	 * Sorts the values in the array based on variable ascending using
	 * HeapSort algorithm
	 *
	 * @param array String array
	 * @param ascending controls order of sort
	 */
	@Override
	public void sort(String[] array, boolean ascending) {
		// build the max heap
		StringHeap heap = new StringHeap(array);
		for (int i = 0; i < array.length; i++) {
			array[i] = heap.heappop();
		}

		// if ascending, reverse the array
		if (ascending) {
			for (int i = 0; i < array.length / HALF_DIVISOR; i++) {
				String tmp = array[i];
				array[i] = array[array.length - i - 1];
				array[array.length - i - 1] = tmp;
			}
		}
	}

}
