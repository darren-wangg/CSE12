/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, piazza
 */
package com.cse.ds.sort;
import com.cse.ds.Comparator;
import com.cse.ds.Sorting;

/**
 * MergeSort Algorithm
 *
 * @param <T>
 */
public class MergeSort<T> implements Sorting<T> {

	private Comparator<T> comparator;

	/**
	 * MergeSort cotr, intializes Comparator interface
	 *
	 * @param comparator comparator interface to implement
	 */
	public MergeSort(Comparator<T> comparator) {
	  this.comparator = comparator;
	}

	/**
	 * Divides the main array
	 *
	 * @param array array of generic type to be sorted
	 * @param left leftmost index
	 * @param right rightmost index
	 * @param ascending ascending or descending order
	 */
	public void mergeSort(T[] array, int left, int right,
	                      boolean ascending) {
	  // base case
	  if (left < right) {
	  int midpoint = (left + right) / 2;
	  mergeSort(array, left, midpoint, ascending);
	  mergeSort(array, midpoint + 1, right, ascending);
	  merge(array, left, midpoint, right, ascending);
	  }
	}

	/**
	 * Sorts the divided arrays and merges in order
	 *
	 * @param array array of generic type to be sorted
	 * @param left leftmost index
	 * @param mid midpoint index
	 * @param right rightmost index
	 * @param ascending ascending or descending order
	 */
	public void merge(T[] array, int left, int mid, int right,
	                  boolean ascending) {
	  // keep track sizes subarrays
	  int size1 = mid - left + 1;
	  int size2 = right - mid;
	  // create subarrays
	  T[] half1 = (T[])new Object[size1];
	  T[] half2 = (T[])new Object[size2];
	  // initialize subarrays with copied values
	  for (int i = 0; i < size1; i++) {
	    half1[i] = array[left + i];
	  }
	  for (int j = 0; j < size2; j++) {
	    half2[j] = array[mid + 1 + j];
	  }

	  // merge subarrays
	  int i,j;
	  i = j = 0;
	  // INITIAL Subarray index (not zero)
	  int k = left;
	  // until subarray indices fill size of array
	  while (i < size1 && j < size2) {
	    // ascending order
	    if (ascending) {
	      if (comparator.comparison(half1[i], half2[j], true)) {
	        // add smalller element to front
	        array[k] = half1[i];
	        i++;
	      } else {
	        array[k] = half2[j];;
		j++;
	      }
	    }

	    // descending order
	    else {
	      if (comparator.comparison(half1[i], half2[j], false)) {
	        // add larger element to front
		array[k] = half1[i];
		i++;
	      } else {
	        array[k] = half2[j];
		j++;
	      }
	    }
	    k++;
	  }

	  // copy over any remaining elements
	  while (i < size1) {
	    array[k] = half1[i];
	    i++;
	    k++;
	  }
	  while (j < size2) {
	    array[k] = half2[j];
	    j++;
	    k++;
	  }

	}

	/**
	 * Calls mergeSort passing in the entire array
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
	  this.mergeSort(array, 0, array.length - 1, ascending);
	}
	
}
