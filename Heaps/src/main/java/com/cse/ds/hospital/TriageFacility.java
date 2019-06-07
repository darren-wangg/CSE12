/**
 * Name: Darren Wang
 * Userid: cs12sp19kj
 * Email: d5wang@ucsd.edu
 * Sources: writeup, Piazza
 */
package com.cse.ds.hospital;

import com.cse.ds.heaps.Heap;
import com.cse.ds.heaps.Tuple;

/**
 * Implements a TriageFacility service.
 */
public class TriageFacility {
	
	private Heap heap;

	private static final int PATIENT_PRIORITY = 95;
	
	/**
	 * Initializes a TriageFacility by constructing a Generic heap
	 * priority: integer, patient priority
	 * value: String, patient name
	 *
	 * @param patients String array of patient data, including priority and
	 * value
	 */
	public TriageFacility(String[] patients) {
		// split input into value and priority
		String[] names = new String[patients.length];
		int[] priorities = new int[patients.length];
		Tuple[] data = new Tuple[patients.length];
		for (int i = 0; i < patients.length; i++) {
			String patient = patients[i];
			int index = patient.indexOf(":");
			names[i] = patient.substring(0,index);
			priorities[i] = Math.abs(PATIENT_PRIORITY -
			  Integer.parseInt(patient.substring(index + 1)));
			data[i] = new Tuple(priorities[i], names[i]);
		}
		// construct heap using array of Tuples
		heap = new Heap(data);
	}
	
	/**
	 * Removes and returns patient with highest priority from the
	 * TriageFacility
	 *
	 * @return value of patient with highest priority
	 */
	public String serviceNextPatient() {
		return (String) heap.heappop().value;
	}
	
	/**
	 * Input a new patient and add to the heap in correct order
	 *
	 * @param patient new patient as a String
	 */
	public void addNewPatient(String patient) {
		// separate priority and value
		int index = patient.indexOf(":");
		String name = patient.substring(0,index);
		int priority = Math.abs(PATIENT_PRIORITY -
		  Integer.parseInt(patient.substring(index + 1)));
		Tuple data = new Tuple(priority, name);
		heap.heappush(data);
	}
	
	/**
	 * Returns number of patients not yet serviced in the TriageFacility
	 *
	 * @return heap size
	 */
	public int getTriageLength() {
		return heap.getSize();
	}
}
