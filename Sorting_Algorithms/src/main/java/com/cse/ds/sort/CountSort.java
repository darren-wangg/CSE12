/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, piazza
 */
package com.cse.ds.sort;
import com.cse.ds.Sorting;
import java.util.*;

/**
 * CountSort Algorithm
 *
 * @param <T>
 */
public class CountSort<T> implements Sorting<T> {
	
	private T[] order;
	
	/**
	 * CountSort cotr, initializes ascending order array
	 *
	 * @param order ascending order of array
	 */
	public CountSort(T[] order) {
	  this.order = order;
	}
	
	/**
	 * CountSort implementation, keeps track of occurrences and sorts based
	 * on count and order array
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

	  // count array
	  int[] count = new int[order.length];
	  // start at order, loop through array counting occurrences
	  for (int i = 0; i < order.length; i++) {
	    for (int j = 0; j < array.length; j++) {
	      if (order[i].equals(array[j])) {
	        count[i]++;
	      }
	    }
	  }

	  // fill appropriate elements in correct positions
	  int index = 0;
	  int i = 0;
	  // ascending order
	  if (ascending) {
	    while (i < count.length) {
	      // fill in array while count > 0
	      if (count[i] != 0) {
	        array[index] = order[i];
	        index++;
	        count[i]--;
	      }
	      else if (count[i] == 0) {
	        i++;
	      }
	    }
	  }

	  // descending order
	  else {
	    i = order.length - 1;
	    while (i >= 0) {
	      // fill in array while count > 0
	      if (count[i] != 0) {
	        array[index] = order[i];
	        index++;
	        count[i]--;
	      }
	      else if (count[i] == 0) {
	        i--;
	      }
	    }
	  }

	}
}
