/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, piazza
 */
package com.cse.ds.comparator;
import com.cse.ds.Comparator;
import java.util.*;

/**
 * Implements Comparator interface to compare Integers
 */
public class IntegerComparator implements Comparator<Integer> {

    /**
     * Comparison of two Integers to determine ordering
     * 
     * @param x Integer, wrapper class for primitive int
     * @param y Integer, wrapper class for primitive int
     * @param ascending ascending or descending order
     * @return x and y in ascending or descending, !ascending, order
     */
    @Override
    public boolean comparison(Integer x, Integer y, boolean ascending) {
        // null args
	if (x == null || y == null) {
	  throw new NullPointerException();
	}

        // ascending order
	if (ascending) {
	    return y.compareTo(x) > 0;
	}
	// descending order
	else {
	    return x.compareTo(y) > 0;
	}
    }
}
