package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickSort {
	
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
	 * Method for swapping two given number values of any array
	 */
	private static int[] swap(int[] intArray, int a, int b) {
		int key = intArray[a];
		intArray[a] = intArray[b];
		intArray[b] = key;
		return intArray;
	}
	
	
	/*
	 * Partition procedure implementing right-most pivot selection
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
	 * Implementation for the pseudocode given for quicksort in the notes
	 */
	public static int[] quickSort(int[] intArray, int p, int r) {
		if (p < r) {
			int partitionResult = partition(intArray, p, r);
			quickSort(intArray, p, partitionResult-1);
			quickSort(intArray, partitionResult+1, r);
		}
		return intArray;
	}
	
	/*
	 * Main method for reading sample files and checking if they are sorted after
	 * the sorting algorithm is applied; measures the time the sorting takes also.
	 */
	public static void main(String... args) {
		String[] files = { "int10", "int50", "int100", "int1000", "int20k", "int500k", "intBig", "dutch" };
		System.out.println("---------------------------");
		System.out.println("QUICK SORT");
		for (String file : files) {
			try {
				System.out.println("---------------------------");
				System.out.print("File Name: ");
				System.out.println(file);
				int[] intArray = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = intArray.length;
				System.out.print("Is Sorted? ");
				long startTime = System.nanoTime();
				int[] quickSorted = quickSort(intArray, 0, n-1);
				long endTime = System.nanoTime();
				System.out.println(isSorted(quickSorted));
				System.out.println("Time taken: " + (endTime - startTime) + "ns");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}
