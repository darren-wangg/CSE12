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
 * Implements Comparator interface to compare Colors
 */
public class ColorComparator implements Comparator<String> {

    /**
     * Comparison of two String colors to determine ordering
     * 
     * @param x String color
     * @param y String color
     * @param ascending ascending or descending order
     * @return x and y in ascending or descending, !ascending, order
     */
    @Override
    public boolean comparison(String x, String y, boolean ascending) {
        // null args
	if (x == null || y == null) {
	    throw new NullPointerException();
	}

	// x same as y
	if (x.toUpperCase().equals(y.toUpperCase())) {
	    return false;
	}

	// ascending order
	if (ascending) {
	    switch (x.toUpperCase()) {
	        // red cannot come before y, it is the highest value
	        case "RED":
	            return false;
		// only red before white
		case "WHITE":
	            if (y.toUpperCase().equals("RED")) {
		        return true;
		    }
		    else {
		        return false;
		    }
		// everything larger than blue
		case "BLUE":
	            return true;
	    }
	}
	// descending order
	else {
	    switch (x.toUpperCase()) {
	        // red is the largest, will always come first if descending
		case "RED":
		    return true;
		// only blue after white
		case "WHITE":
		    if (y.toUpperCase().equals("BLUE")) {
		        return true;
		    }
		    else {
			return false;
		    }
		// everything smaller than blue
		case "BLUE":
		    return false;
	    }
	}
	return false;
    }
}
