package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MedianOfThreeQuickSort {
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
	 * Implementation of a quick-sort variant using the median of three partitioning scheme
	 */
	public static int[] medianOfThreeQuickSort(int[] intArray, int p, int r) {
		if (p < r) {
			int c = (p+r) / 2;
			if (intArray[r] < intArray[p])
				intArray = swap(intArray, r, p);
			if (intArray[c] < intArray[p])
				intArray = swap(intArray, c, p);
			if (intArray[r] < intArray[c])
				intArray = swap(intArray, r, c);
			int q = partition(intArray, p, r);
			medianOfThreeQuickSort(intArray, p, q-1);
			medianOfThreeQuickSort(intArray, q+1, r);
		}
		return intArray;
	}
		
	/*
	 * Method to generate pathological input sequences for the Quicksort algorithm,
	 * considering a median-of-three partitioning scheme.
	 */
	public static int[] pathologicalInput(int value) {
        int[] patholArray = new int[value]; //Initialise pathological array.
        
        int i = 0; //Index value counter
        for(int num=0; num < value; num++) {
          if(num%2 == 0){ //All even numbers get added to the left positions of A.
            patholArray[i] = num;
            i++;
          }
          else { //All odd numbers get added to the right positions of A.
            patholArray[value-i] = num;
          }
        }
        return patholArray;
    }
	
	/*
	 * To give a prettier look to the pathological array 
	 */
	private static void prettyPrint(int[] intArray) {
		int n = intArray.length;
		for (int i=0; i < n; ++i)
			System.out.print(intArray[i]+" ");
		System.out.println();
	}
	
	/*
	 * Main method for reading sample files and checking if they are sorted after
	 * the sorting algorithm is applied; measures the time the sorting takes also.
	 * Furthermore, includes an example of pathological input, however uncomment
	 * the line of code for it work.
	 */
	public static void main(String... args) {
		//prettyPrint(pathologicalInput(15));      // For Part 3
		String[] files = {"int10", "int50","int100","int1000","int20k", "int500k", "intBig", "dutch"};
		System.out.println("--------------------------");
		System.out.println("MEDIAN OF THREE QUICK SORT");
		for(String file: files) {
			try {
				System.out.println("--------------------------");
				System.out.print("File Name: ");
				System.out.println(file);
				int[] intArray = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = intArray.length;
				System.out.print("Is Sorted? ");
				long startTime = System.nanoTime();
				int[] med3QS = medianOfThreeQuickSort(intArray, 0, n-1);
				long endTime = System.nanoTime();
				System.out.println(isSorted(med3QS));
				System.out.println("Time taken: "+(endTime - startTime)+"ns");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
