package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickInsertionSort {

	/*
	 * Method used to open the files and read off the numbers in the arrays
	 */
	private static int[] readFile(String filePath) throws IOException {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		try (Scanner s = new Scanner(new File(filePath))) {
			while(s.hasNext()) {
				int parsedNum = Integer.parseInt(s.nextLine());
				arrayList.add(parsedNum);
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist.");
		}
		int[] intArray = new int[arrayList.size()];
		for (int i=0; i < intArray.length; i++) {
			intArray[i] = arrayList.get(i).intValue();
		}
		return intArray;
	}
	
	/*
	 * Method to check if a given array is sorted or not
	 */
	public static boolean isSorted(int[] intArray) {
		int n = intArray.length;
		for (int i=0; i < n-1; i++) {
			if (intArray[i] > intArray[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Method for swapping two given number values of an array
	 */
	private static int[] swap(int[] intArray, int a, int b) {
		int key = intArray[a];
		intArray[a] = intArray[b];
		intArray[b] = key;
		return intArray;
	}
	
	
	/*
	 * Implementation of insertion sort 
	 */
	private static int[] insertionSort(int[] intArray, int p, int r) {
		for (int i=p+1; i <= r; i++) {
			int key = intArray[i];
			int j = i-1;
			while (j >= 0 && key < intArray[j]) {
				intArray[j+1] = intArray[j];
				j--;
			}
			intArray[j+1] = key;
		}
		return intArray;
	}
	
	/*
	 * Partition procedure reused
	 */
	private static int partition(int[] intArray, int p, int r) {
		int x = intArray[r];
		int i = p-1;
		for (int j=p; j < r; j++) {
			if (intArray[j] <= x) {
				i += 1;
				intArray = swap(intArray, i, j);
			}
		}
		intArray = swap(intArray, i+1, r);
		return i+1;
	}
	
	/*
	 * Implementation of a quick-sort variant which returns 
	 * without sorting subarrays with fewer than k elements,
	 * then uses INSERTION-SORT to sort the entire nearly-sorted array, based on notes
	 */
	public static int[] quickInsertionSort(int[] intArray, int p, int r, int k) {
		try {
			if (p+k < r) {
				int q = partition(intArray, p, r);
				quickInsertionSort(intArray, p, q-1, k);
				quickInsertionSort(intArray, q+1, r, k);
			}
			intArray = insertionSort(intArray, p, r);
		}
		catch(Exception StackOverflowError) {
			System.out.println("A Stack Overflow occurred.");
		}
		return intArray;
	}
	
	/*
	 * Main method for reading sample files and checking if they are sorted after
	 * the sorting algorithm is applied; measures the time the sorting takes also.
	 */	
	public static void main(String... args) {
		String[] files = {"int10", "int50","int100","int1000","int20k", "int500k", "intBig", "dutch"};
		System.out.println("---------------------");
		System.out.println("QUICK INSERTION SORT");
		for(String file: files) {
			try {
				System.out.println("---------------------");
				System.out.print("File Name: ");
				System.out.println(file);
				int[] intArray = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = intArray.length;
				System.out.print("Is Sorted? ");
				long startTime = System.nanoTime();
				int[] quiknserSorted = quickInsertionSort(intArray, 0, n-1, 1000);
				long endTime = System.nanoTime();
				System.out.println(isSorted(quiknserSorted));
				System.out.println("Time taken: "+(endTime - startTime)+"ns");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
