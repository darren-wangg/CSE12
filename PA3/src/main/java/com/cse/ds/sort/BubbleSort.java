/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, piazza
 */
package com.cse.ds.sort;
import com.cse.ds.Comparator;
import com.cse.ds.Sorting;
import java.util.*;

/**
 * BubbleSort Algorithm
 *
 * @param <T>
 */
public class BubbleSort<T> implements Sorting<T> {
	
	private Comparator<T> comparator;
	
	/**
	 * BubbleSort cotr, initializes Comparator interface
	 *
	 * @param comparator Comparator interface to implement
	 */
	public BubbleSort(Comparator<T> comparator) {
	  // initialize comparator to param comp
	  this.comparator = comparator;
	}
	
	/**
	 * BubbleSort implementation, swaps two adjacent elements at a time
	 *
	 * @param array array of generic type to be sorted
	 * @param ascending ascending or descending order
	 */
	@Override
	public void sort(T[] array, boolean ascending) {
	  // null arg
	  if (array == null) {
	    throw new NullPointerException();
	  }

	  // array was swapped
	  boolean swap = false;
	  for (int i = 0; i < array.length; i++) {
	    for (int j = 0; j < array.length - 1 - i; j++) {

	      // ascending order
	      if (ascending) {
	        // not sorted, if comp = true, no need to swap
		if (!comparator.comparison(array[j], array[j + 1],
		    ascending)) {
		  // swap two elements
		  this.swap(array, j, j + 1);
		  swap = true;
		}
	      }

	      // descending order
	      else {
	        // not sorted, if comp = true, no need to swap
	        if (!comparator.comparison(array[j], array[j + 1],
	            false)) {
		  // swap two elements
		  this.swap(array, j, j + 1);
		  swap = true;
	        }
	      }

	    }
	    // if gone through entire list without a swap = already sorted
	    if (!swap) {
	      return;
	    }
	  }
	}

}
