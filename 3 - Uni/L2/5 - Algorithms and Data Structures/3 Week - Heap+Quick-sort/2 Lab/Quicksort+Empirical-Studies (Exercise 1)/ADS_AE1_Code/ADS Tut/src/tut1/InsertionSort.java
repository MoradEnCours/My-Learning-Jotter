package tut1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {
	public static int[] insertionSort(int[] nums) {
		for (int j=1; j < nums.length; j++) {
			int key = nums[j];
			//System.out.println(key);
			int i = j-1;
			//System.out.println(i);
			while (i >= 0 && nums[i] > key) {
				//System.out.println(nums[i+1] =  nums[i]);
				nums[i+1] =  nums[i];
				i -= 1;
				//System.out.println(i);
			}
			nums[i+1] = key;
			//System.out.println(nums[i+1] = key);
		}
		return nums;
	}
	
	private static int[] readFile(String filePath) throws IOException{
		ArrayList<Integer> arrayList = new ArrayList<>();
		try (Scanner s = new Scanner(new File(filePath))) {
		    while (s.hasNext()) {
		    	int p = Integer.parseInt(s.nextLine());
		        arrayList.add(p);
		    }
		}
		catch (FileNotFoundException e) {
		}
		int[] A = new int[arrayList.size()];
	    for (int i=0; i < A.length; i++)
	    {
	        A[i] = arrayList.get(i).intValue();
	    }
	    return A;
	}
	
	public static boolean isSorted(int a[]){
		int n = a.length;
		for (int i = 0; i < n-1; i++){
			if (a[i] > a[i+1]){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] a = {5,2,4,6,1,3};
		//System.out.println(Arrays.toString(insertionSort(a)));
		String[] files = {"int10", "int50","int100","int1000","int20k", 
				"int500k", "intBig", "dutch"};
		System.out.println("---------------------");
		System.out.println("INSERTION SORT");
		for(String file: files) {
			try {
				System.out.println("---------------------");
				System.out.print("File Name: ");
				System.out.println(file);
				int[] A = readFile(System.getProperty("user.dir")+ "\\Files\\" + file +".txt");
				int n = A.length;
				System.out.print("Is Sorted? ");
				long time1 = System.currentTimeMillis();
				long startTime = System.nanoTime();
				int[] B = insertionSort(a);
				long time2 = System.currentTimeMillis();
				long endTime = System.nanoTime();
				System.out.println(isSorted(B));
				System.out.println("time taken: " + (time2 - time1)+" ns");
				System.out.println("time taken: "+(endTime - startTime)+" ns");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	
}
