package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ThreeWayQuickSort {
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
			System.out.println("Le fichier n'existe pas.");
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
	 * Implementation of a partition of three, having left side, middle and right side
	 * With left values to pivot being less and values to the right being more
	 * 
	 */
	private static int[] partitionThree(int[] intArray, int p, int r) {
		int leftPart = p;
		int i = p;
		int rightPart = r;
		int x = intArray[r];
		while (i <= rightPart) {
			if (intArray[i] < x) {
				swap(intArray, leftPart, i);
				leftPart++;
				i++;
			}
			else if (intArray[i] > x) {
				swap(intArray, rightPart, i);
				rightPart--;
			}
			else {
				i++;
			}
		}
		int[] partition = {leftPart, rightPart};
		return partition;
	}
	
	/*
	 * Implementation for 3-way-quicksort
	 */
	public static int[] threeWayQuickSort(int[] intArray, int p, int r) {
		if (p < r) {
			int[] partition = partitionThree(intArray, p, r);
			threeWayQuickSort(intArray, p, partition[0]-1);
			threeWayQuickSort(intArray, partition[1], partition[0]);
			threeWayQuickSort(intArray, partition[1]+1, r);
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
		System.out.println("THREE WAY QUICK SORT");
		for(String file: files) {
			try {
				System.out.println("---------------------");
				System.out.print("File Name: ");
				System.out.println(file);
				int[] intArray = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = intArray.length;
				System.out.print("Is Sorted? ");
				long startTime = System.nanoTime();
				int[] threeWayQS = threeWayQuickSort(intArray, 0, n-1);
				long endTime = System.nanoTime();
				System.out.println(isSorted(threeWayQS));
				System.out.println("Time taken: "+(endTime - startTime)+"ns");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
