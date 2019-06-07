package com.cse.ds;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.cse.ds.heaps.Heap;
import com.cse.ds.heaps.HeapSortGeneric;
import com.cse.ds.heaps.Tuple;
import com.cse.ds.hospital.TriageFacility;
import com.cse.ds.sorting.HeapSortString;
import com.cse.ds.sorting.Sorting;

import java.util.*;
import com.cse.ds.sorting.StringHeap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GenericTest {
	
	static Heap heap = null;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		heap = new Heap();
		heap.heappush(new Tuple<String>(1,"AANDY"));
		heap.heappush(new Tuple<String>(2,"BANDY"));
		heap.heappush(new Tuple<String>(3,"CANDY"));
		heap.heappush(new Tuple<String>(4,"DANDY"));
		heap.heappush(new Tuple<String>(5,"EANDY"));
		heap.heappush(new Tuple<String>(6,"FANDY"));
		heap.heappush(new Tuple<String>(7,"GANDY"));
		heap.heappush(new Tuple<String>(8,"HANDY"));
    }
	

	@Test
	public void test1() {
		Assert.assertEquals(8,heap.getSize());
	}
	
	@Test
	public void test2( ) {
		Assert.assertEquals("HANDY",heap.getMax().value);
	}
	
	@Test
	public void test3( ) {
		Assert.assertEquals("HANDY",heap.heappop().value);
		Assert.assertEquals("GANDY",heap.getMax().value);
	}
	
	@Test
	public void test4( ) {
		heap.increasePriority(5, 100);
		Assert.assertEquals("CANDY",heap.getMax().value);
	}
	
	@Test
	public void test5( ) {
		Tuple<Integer> arr[] = new Tuple[] {new Tuple(3,1), new Tuple(2,2), new Tuple(1,3), new Tuple(9,4), new Tuple(4,5), new Tuple(8,6)};
		heap = new Heap(arr);
		Assert.assertEquals("[null, (9,4), (4,5), (8,6), (2,2), (3,1), (1,3)]",heap.toString());
	}
	
	@Test
	public void test6( ) {
		String color[] = new String[] {"WHITE","RED","BLUE"};
		int priority[] = new int[] {1,2,0};
		HeapSortGeneric sorting = new HeapSortGeneric<>(color, priority);
		Assert.assertArrayEquals(new String[] {"RED","WHITE","BLUE"},sorting.sort(false));
		Assert.assertArrayEquals(new String[] {"BLUE","WHITE","RED"},sorting.sort(true));
	}
	
	@Test
	public void test7( ) {
		String array[] = new String[] {"A","C","F","B","E","D"};
		Sorting sorting = new HeapSortString();
		sorting.sort(array, true);
		Assert.assertArrayEquals(new String[] {"A","B","C", "D", "E", "F"},array);
	}
	
	@Test
	public void test8( ) {
		String array[] = new String[] {"A","C","F","B","E","D"};
		Sorting sorting = new HeapSortString();
		sorting.sort(array, false);
		Assert.assertArrayEquals(new String[] {"F","E","D", "C", "B", "A"},array);
	}
	
	@Test
	public void test9( ) {
		String patients[] = new String[] {"A:1","C:3","F:6","B:2","E:5","D:4"};
		TriageFacility triage = new TriageFacility(patients);
		Assert.assertEquals(6,triage.getTriageLength());
		Assert.assertEquals("A",triage.serviceNextPatient());
		triage.addNewPatient("A:1");
		Assert.assertEquals(6,triage.getTriageLength());
		Assert.assertEquals("A",triage.serviceNextPatient());
	}
	
	@Test
	public void test10( ) {
		String patients[] = new String[] {"A:1","C:3","F:6","B:2","E:5","D:4"};
		TriageFacility triage = new TriageFacility(patients);
		Assert.assertEquals("A",triage.serviceNextPatient());
		Assert.assertEquals("B",triage.serviceNextPatient());
		triage.addNewPatient("CSE:12");
		Assert.assertEquals(5,triage.getTriageLength());
	}

	// added tests
	@Test
	public void testHeap() {
		Tuple<String> arr[] = new Tuple[] {new Tuple(1,"one"), new Tuple(2,"two"), new Tuple(3,"three"), new Tuple(9,"nine"), new Tuple(4,"four"), new Tuple(8,"eight")};
		heap = new Heap(arr);
		Assert.assertEquals(6, heap.getSize());
		Assert.assertEquals("nine", heap.getMax().value);
		Assert.assertEquals("nine", heap.heappop().value);
		Assert.assertEquals(5, heap.getSize());
		Assert.assertEquals("eight", heap.heappop().value);
		heap.heappush(new Tuple(10,"ten"));
		Assert.assertEquals(5, heap.getSize());
		Assert.assertEquals("ten", heap.getMax().value);
		Assert.assertEquals("ten", heap.heappop().value);
		Assert.assertEquals("four", heap.heappop().value);
	}

	@Test
	public void testStringHeap() {
		String arr[] = new String[] {"A","B","D","C","F","E"};
		StringHeap heap = new StringHeap(arr);
		Assert.assertEquals(6, heap.getSize());
		Assert.assertEquals("F", heap.getMax());
		Assert.assertEquals("F", heap.heappop());
		Assert.assertEquals(5, heap.getSize());
		Assert.assertEquals("E", heap.heappop());
		Assert.assertEquals(4, heap.getSize());
		Assert.assertEquals("D", heap.getMax());
	}
}
