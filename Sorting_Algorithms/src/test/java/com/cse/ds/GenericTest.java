package com.cse.ds;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cse.ds.comparator.IntegerComparator;
import com.cse.ds.comparator.StringComparator;
import com.cse.ds.comparator.ColorComparator;
import com.cse.ds.comparator.StringComparatorReverse;
import com.cse.ds.comparator.StudentComparator;

import com.cse.ds.sort.BubbleSort;
import com.cse.ds.sort.CountSort;
import com.cse.ds.sort.SelectionSort;
import com.cse.ds.sort.MergeSort;

import java.util.*;

/**
 * 
 *
 */
public class GenericTest {
	
    static BubbleSort<Integer> bubbleSortInteger;
    static SelectionSort<Integer> selectionSortInteger;
    static MergeSort<Integer> mergeSortInteger;
    static CountSort<Integer> countSortInteger;

    Integer arr1[], arr1r[];// = new Integer[10];
    Integer arr2[], arr2r[];
	
    @BeforeClass
    public static void setUpBeforeClass() {
	Integer order[] = {0,1,2};
	bubbleSortInteger = new BubbleSort<>(new IntegerComparator());
	selectionSortInteger = new SelectionSort<>(new IntegerComparator());
	mergeSortInteger = new MergeSort<>(new IntegerComparator());
	countSortInteger = new CountSort<>(order);
    }
	
    @Before
    public void populatePlayList(){
    	arr1 = new Integer[]{5,6,7,3,2,4,5,5,5,3,2,6,7,8,10};
	arr2 = new Integer[]{1,2,0,1,0,2};
	arr1r = new Integer[]{2,2,3,3,4,5,5,5,5,6,6,7,7,8,10};
	arr2r = new Integer[]{2,2,1,1,0,0};
    }
    
    /* @Test
    public void testSelectionIntegerAscending()
    {
    	selectionSortInteger.sort(arr1, true);
    	System.out.println(Arrays.toString(arr1));
    	System.out.println(Arrays.toString(arr1r));
    	Assert.assertArrayEquals(arr1r, arr1);
    }

    @Test
    public void testSelectionIntegerDescending()
    {
        Integer[] arr1r = new Integer[] {10,8,7,7,6,6,5,5,5,5,4,3,3,2,2};
    	selectionSortInteger.sort(arr1, false);
    	System.out.println(Arrays.toString(arr1));
    	System.out.println(Arrays.toString(arr1r));
    	Assert.assertArrayEquals(arr1r, arr1);
    }

    @Test
    public void testSelectionStringAscending() {
        SelectionSort<String> selectionSortString =
	    new SelectionSort<String>(new StringComparator());
	String[] arr1S = new String[] {"a","z","s","e","b","d","r"};
	String[] arr1Sr = new String[] {"a","b","d","e","r","s","z"};
	selectionSortString.sort(arr1S, true);
	System.out.println(Arrays.toString(arr1S));
	System.out.println(Arrays.toString(arr1Sr));
	Assert.assertArrayEquals(arr1S, arr1Sr);
    }

    @Test
    public void testSelectionColorAscending() {
      SelectionSort<String> selectionSortColor =
          new SelectionSort<String>(new ColorComparator());
      String[] arrC = new String[] {"Red", "Blue", "White", "Blue"};
      String[] arrCr = new String[] {"Blue", "Blue", "White", "Red"};
      selectionSortColor.sort(arrC, true);
      System.out.println(Arrays.toString(arrC));
      System.out.println(Arrays.toString(arrCr));
      Assert.assertArrayEquals(arrC, arrCr);
    }

    @Test
    public void testBubbleIntegerAscending()
    {
    	bubbleSortInteger.sort(arr1, true);
    	System.out.println(Arrays.toString(arr1));
    	System.out.println(Arrays.toString(arr1r));
    	Assert.assertArrayEquals(arr1r, arr1);
    }

    @Test
    public void testBubbleIntegerDescending() {
        Integer[] arr1r = new Integer[] {10,8,7,7,6,6,5,5,5,5,4,3,3,2,2};
    	bubbleSortInteger.sort(arr1, false);
    	System.out.println(Arrays.toString(arr1));
    	System.out.println(Arrays.toString(arr1r));
    	Assert.assertArrayEquals(arr1r, arr1);
    }    

    @Test
    public void testBubbleStringAscending() {
        BubbleSort<String> bubbleSortString =
	    new BubbleSort<String>(new StringComparator());
	String[] arr1S = new String[] {"a","z","s","e","b","d","r"};
	String[] arr1Sr = new String[] {"a","b","d","e","r","s","z"};
	bubbleSortString.sort(arr1S, true);
	System.out.println(Arrays.toString(arr1S));
	System.out.println(Arrays.toString(arr1Sr));
	Assert.assertArrayEquals(arr1S, arr1Sr);
    }

    @Test
    public void testMerge() {
      mergeSortInteger.sort(arr1, true);
      System.out.println(Arrays.toString(arr1));
      System.out.println(Arrays.toString(arr1r));
      Assert.assertArrayEquals(arr1, arr1r);
    }

    @Test
    public void testMergeIntegerAscending() {
      Integer[] arr1 = new Integer[] {38,27,43,3,9,82,10};
      Integer[] arr1r = new Integer[] {3,9,10,27,38,43,82};
      mergeSortInteger.sort(arr1, true);
      System.out.println(Arrays.toString(arr1));
      System.out.println(Arrays.toString(arr1r));
      Assert.assertArrayEquals(arr1, arr1r);
    }

    @Test
    public void testCountIntegerAscending()
    {
        Integer[] testArr = new Integer[] {0,2,4,5,1,3,5,4,6,7,8,10,9};
	Integer[] orderInt = new Integer[] {0,1,2,3,4,5,6,7,8,9,10};
	Integer[] testArrOrder = new Integer[] {0,1,2,3,4,4,5,5,6,7,8,9,10};
	CountSort<Integer> countSortInt = new CountSort<Integer>(orderInt);
    	countSortInt.sort(testArr, true);
    	System.out.println("Actual: " + Arrays.toString(testArr));
    	System.out.println("Expected: " + Arrays.toString(testArrOrder));
    	Assert.assertArrayEquals(testArr, testArrOrder);
    }

    // original
    @Test
    public void testCountIntegerDescending()
    {
    	countSortInteger.sort(arr2, false);
    	System.out.println("Actual: " + Arrays.toString(arr2));
    	System.out.println("Expected: " + Arrays.toString(arr2r));
    	Assert.assertArrayEquals(arr2r, arr2);
    }

    @Test
    public void testCountStringAscending()
    {
        String[] testArr = new String[] {"Mango", "Apple", "Apple", "Lime",
	                               "Banana", "Lime", "Pear"};
	String[] orderString = new String[] {"Apple", "Banana", "Lime",
	                                     "Mango", "Pear", "Watermelon"};
	String[] testArrOrder = new String[] {"Apple", "Apple", "Banana",
	                                      "Lime", "Lime", "Mango", "Pear"};
	CountSort<String> countSortString = new CountSort<String>(orderString);
    	countSortString.sort(testArr, true);
    	System.out.println("Actual: " + Arrays.toString(testArr));
    	System.out.println("Expected: " + Arrays.toString(testArrOrder));
    	Assert.assertArrayEquals(testArr, testArrOrder);
    }

    @Test
    public void testCountStringDescending() {
        String[] testArr = new String[] {"Mango", "Apple", "Apple", "Lime",
	                                 "Banana", "Lime", "Pear"};
	String[] orderString = new String[] {"Apple", "Banana", "Lime",
	                                     "Mango", "Pear", "Watermelon"};
	String[] testArrOrder = new String[] {"Pear", "Mango", "Lime", "Lime",
	                                      "Banana", "Apple", "Apple"};
	CountSort<String> countSortString = new CountSort<String>(orderString);
    	countSortString.sort(testArr, false);
    	System.out.println("Actual: " + Arrays.toString(testArr));
    	System.out.println("Expected: " + Arrays.toString(testArrOrder));
    	Assert.assertArrayEquals(testArr, testArrOrder);
    } */

    @Test
    public void testBubbleSortRuntime() {
        // create test array
        Integer[] array = new Integer[1000];
	for (int i = 0; i < array.length; i++) {
	    array[i] = (int)(Math.random() * 10);
	}
	long start;
	long end;
	long time_taken;
	// time BubbleSort
        start = System.currentTimeMillis();
	bubbleSortInteger.sort(array,true);	
	end = System.currentTimeMillis();
	time_taken = end - start;
	System.out.println("Bubble-Sorting time :" + time_taken + "ms");
    }

    @Test
    public void testSelectionSortRuntime() {
        // create test array
        Integer[] array = new Integer[1000];
	for (int i = 0; i < array.length; i++) {
	    array[i] = (int)(Math.random() * 10);
	}
	long start;
	long end;
	long time_taken;
	// time SelectionSort
        start = System.currentTimeMillis();
	selectionSortInteger.sort(array,true);	
	end = System.currentTimeMillis();
	time_taken = end - start;
	System.out.println("Selection-Sorting time :" + time_taken + "ms");
    }

    @Test
    public void testMergeSortRuntime() {
        // create test array
        Integer[] array = new Integer[1000];
	for (int i = 0; i < array.length; i++) {
	    array[i] = (int)(Math.random() * 10);
	}
	long start;
	long end;
	long time_taken;
	// time MergeSort
        start = System.currentTimeMillis();
	mergeSortInteger.sort(array,true);	
	end = System.currentTimeMillis();
	time_taken = end - start;
	System.out.println("Merge-Sorting time :" + time_taken + "ms");
    }

    @Test
    public void testCountSortRuntime() {
        // create test array
        Integer[] array = new Integer[1000];
	Integer[] order = new Integer[] {0,1,2,3,4,5,6,7,8,9,10};
	CountSort<Integer> countSortInteger = new CountSort<>(order);
	for (int i = 0; i < array.length; i++) {
	    array[i] = (int)(Math.random() * 10);
	}
	long start;
	long end;
	long time_taken;
	// time CountSort
        start = System.currentTimeMillis();
	countSortInteger.sort(array,true);	
	end = System.currentTimeMillis();
	time_taken = end - start;
	System.out.println("Count-Sorting time :" + time_taken + "ms");
    }

}
