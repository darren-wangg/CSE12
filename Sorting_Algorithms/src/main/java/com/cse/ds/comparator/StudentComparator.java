/**
 * Name: Darren Wang
 * Email: d5wang@ucsd.edu
 * Userid: cs12sp19kj
 * Sources: writeup, piazza
 */
package com.cse.ds.comparator;
import com.cse.ds.Comparator;
import com.cse.ds.models.Student;
import java.util.*;

/**
 * Implements Comparator interface to compare Student objects
 */
public class StudentComparator implements Comparator<Student> {

    /**
     * Comparison of two Student objects to determine ordering
     * 
     * @param x Student
     * @param y Student
     * @param ascending ascending or descending order
     * @return x and y in ascending or descending, !ascending, order
     */
    @Override
    public boolean comparison(Student x, Student y, boolean ascending) {
        // null args
	if (x == null || y == null) {
	  throw new NullPointerException();
	}

        int xPID = Integer.parseInt(x.getPID());
	int yPID = Integer.parseInt(y.getPID());
        // ascending order
	if (ascending) {
	    // same CGPA
	    if (y.getCGPA() == x.getCGPA()) {
	        return yPID > xPID;
	    }
	    // different CGPA
	    else {
	        return y.getCGPA() > x.getCGPA();
	    }
	}
	// descending order
	else {
	    // same CGPA
	    if (y.getCGPA() == x.getCGPA()) {
	        return xPID > yPID;
	    }
	    // different CGPA
	    else {
	        return x.getCGPA() > y.getCGPA();
	    }
	}
    }
}
