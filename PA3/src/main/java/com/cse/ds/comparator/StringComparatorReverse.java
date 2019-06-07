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
 * Implements Comparator interface to compare Strings in reverse
 */
public class StringComparatorReverse implements Comparator<String> {

    /**
     * Comparison of two Strings in reverse to determine ordering
     * 
     * @param x String
     * @param y String
     * @param ascending ascending or descending order
     * @return x and y in ascending or descending, !ascending, order
     */
    @Override
    public boolean comparison(String x1, String y1, boolean ascending) {
        // null args
	if (x1 == null || y1 == null) {
	  throw new NullPointerException();
	}

        // reverse strings
	char[] x = x1.toCharArray();
	char[] y = y1.toCharArray();
	// reverse x
	for (int i = 0; i < x.length/2; i++) {
	    char tmp = x[i];
	    x[i] = x[x.length - 1 - i];
	    x[x.length - 1 - i] = tmp;
	}
	// reverse y
	for (int i = 0; i < y.length/2; i++) {
	    char tmp = y[i];
	    y[i] = y[y.length - 1 - i];
	    y[y.length - 1 - i] = tmp;
	}
	String strX = new String(x);
	String strY = new String(y);
	// ascending order
	if (ascending) {
	    return strY.compareTo(strX) > 0;
	}
	// descending order
	else {
	    return strX.compareTo(strY) > 0;
	}
    }
}
