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
 * SelectionSort Algorithm
 *
 * @param <T>
 */
public class SelectionSort<T> implements Sorting<T> {
	
	private Comparator<T> comparator;
	
	/**
	 * SelectionSort cotr, initializes Comparator interface
	 *
	 * @param comparator Comparator interface to implement
	 */
	public SelectionSort(Comparator<T> comparator) {
	  this.comparator = comparator;
	}
	
	/**
	 * SelectionSort implementation, separates into a sorted and unsorted
	 * array, comparing element by element
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

	  // i: last index of sorted list
	  for (int i = 0; i < array.length - 1; i++) {
	    // keep track index of min/max values, and size of sorted list
	    int minIndex = i;
	    int maxIndex = i;

	    for (int j = i + 1; j < array.length; j++) {
	      // next value less than minIndex
	      if (comparator.comparison(array[j], array[minIndex], true)) {
	        minIndex = j;
	      }
	      // next value greater than maxIndex
	      else if (comparator.comparison(array[j], array[maxIndex], false)) {
	        maxIndex = j;
	      }
	    }

	    // ascending order
	    if (ascending) {
	      // swap minimum value and value at end of sorted list
	      this.swap(array, minIndex, i);
	    }
	    // descending order
	    else {
	      // swap maximum value and value at end of sorted list
	      this.swap(array, maxIndex, i);
	    }
	}
      }
}
