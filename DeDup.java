package com.array.dupe;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class DeDup {
	//Define the Integer Array with randam intigers.
		public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
	            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
	            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};

	public static void main(String[] args) {

		try {
		
			
			DeDup deDup=	new DeDup();
			
			
			int[] resultbyUseingSet = removeDupsWithSet(deDup.randomIntegers);
			print(resultbyUseingSet, "Got array of results by using Hash set");
			
			int [] resultArrayMaintainInsertionOrder = removeDuplicateWithSameOrderOWN(deDup.randomIntegers);
			print(resultArrayMaintainInsertionOrder, "Got Array with Insertion Order without Using LinkedHashMap ");
			
			int[] resultbyUseingLinkedHashMap = removeDupsWithLinkedHashMap(deDup.randomIntegers);
			print(resultbyUseingLinkedHashMap, "Got Array with Insertion Order Using LinkedHashMap ");
			
			int [] resultArrayUsingQuickSorting = removeDupsUsingQuickSort(deDup.randomIntegers);
			print(resultArrayUsingQuickSorting, "Got Array by using QuickSort ");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

	}
	

	/**
	 * # 1 Implementation 
	 * Using HashSet  unique elements storage feature
	 * it is Very simple implementation .
	 * @param randomIntegers
	 * @return
	 */
	public static int[] removeDupsWithSet(int[] randomIntegers){
		int[] nonDupeIntegers;
		int randomIntegersLen = randomIntegers.length;
		Set<Integer> set = new HashSet<Integer>();

		for(int i = 0; i < randomIntegersLen; i++){
		  set.add(randomIntegers[i]);
		}
		nonDupeIntegers=new int[set.size()];
		Iterator<Integer> nonDupeIntegersIter = set.iterator();
		int nonDupeIntegerIndex=0;
		while(nonDupeIntegersIter.hasNext()) {
			nonDupeIntegers[nonDupeIntegerIndex]=nonDupeIntegersIter.next();
			nonDupeIntegerIndex=nonDupeIntegerIndex+1;
		}
		
		return nonDupeIntegers;
		
	}
	/**
	 * Implementation 2
	 * Own implementation to 
	 * remove duplicated values in an array and maintain same orginal order 
	 * without useing LinkedHashMap
	 * 
	 * @param randomIntegers
	 * @return
	 */
	public static int[] removeDuplicateWithSameOrderOWN(int[] randomIntegers) {

		//Get the size of array set the end index
	    int end = randomIntegers.length;

	    for (int i = 0; i < end; i++) {
	        for (int j = i + 1; j < end; j++) {
	            if (randomIntegers[i] == randomIntegers[j]) {                  
	                int shiftLeft = j;
	                for (int k = j+1; k < end; k++, shiftLeft++) {
	                	randomIntegers[shiftLeft] = randomIntegers[k];
	                }
	                end--;
	                j--;
	            }
	        }
	    }

	    int[] returnList = new int[end];
	    for(int i = 0; i < end; i++){
	    	returnList[i] = randomIntegers[i];
	    }
	    return returnList;
	}
	

	/**
	 * Implementation 3
	 * A Very simple implementation using LinkedHashMap preserves Insertion order 
	 * @param randomIntegers
	 * @return
	 */
	public static int[] removeDupsWithLinkedHashMap(int[] randomIntegers) {
		// put  the integers into a set
		Map<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i < randomIntegers.length; i++) {
			// Values shoudl be > 0 for Avoiding the Negatives
			if (randomIntegers[i] > 0)
				linkedHashMap.put(randomIntegers[i], randomIntegers[i]);
		}
		// get the elements from the Map  into an array
		int[] insertionOrderedArray = new int[linkedHashMap.size()];
		int i = 0;
		for (Integer num : linkedHashMap.keySet()) {
			insertionOrderedArray[i++] = num;
		}
		return insertionOrderedArray;
	}
	
	
	
	/**
	 * Implementation 4
	 * Quicksort on the given array
	 * It has worst case time complexity of O(n2) but performs lot better
	 * in average case with time complexity of O(n log n). 
	 * This performs better than MergeSort and HeapSort which also 
	 * have same asymptotic time complexity O(n log n) 
	 * @param randomIntegers
	 * @return
	 * @throws Exception
	 */
	public static int[] removeDupsUsingQuickSort(int[] randomIntegers) throws Exception{
		// Sorting the array 
		quickSort(randomIntegers);
		// Removal of duplicates in better time line
		final int[] unqiue = new int[randomIntegers.length];
		int prev = randomIntegers[0];
		unqiue[0] = prev;
		int count = 1;
		for (int i = 1; i < randomIntegers.length; ++i) {
			if (randomIntegers[i] != prev) {
				unqiue[count++] = randomIntegers[i];
			}
			prev = randomIntegers[i];
		}
		final int[] finalArray = new int[count];
		System.arraycopy(unqiue, 0, finalArray, 0, count);
		return finalArray;
	}

	public static void quickSort(final int[] values) throws Exception{
		if (values.length == 0) {
			return;
		}
		quickSort(values, 0, values.length - 1);
	}

	
		/**
		 * Quicksort on the given array
		 * It has worst case time complexity of O(n2) but performs lot better
		 * in average case with time complexity of O(n log n). 
		 * This performs better than MergeSort and HeapSort which also 
		 * have same asymptotic time complexity O(n log n) 
		 * @param values
		 * @param low
		 * @param high
		 * @throws Exception
		 */
		private static void quickSort(final int[] values, final int low, final int high) throws Exception{
		int i = low, j = high;
		//Pick an element – typically this is called pivot.
		int pivot = values[low + (high - low) / 2];
		while (i <= j) {
			while (values[i] < pivot) {
				i++;
			}
			while (values[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swapValue(values, i, j);
				i++;
				j--;
			}
		}
		if (low < j) {
			quickSort(values, low, j);
		}
		if (i < high) {
			quickSort(values, i, high);
		}
	}

		 
	/**
	 * This method will help to swap the values for quicksort
	 * @param values
	 * @param i
	 * @param j
	 * @throws Exception
	 */
	private static void swapValue(final int[] values, final int i, final int j) throws Exception{
		final int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}

	
	
	/**
	 * This method will help to print the array
	 * @param array
	 * @param typeOfSolution
	 */
	private static void print(int[] array, String typeOfSolution){
		System.out.println(typeOfSolution +"  "+ Arrays.toString(array));

	}
	
}
