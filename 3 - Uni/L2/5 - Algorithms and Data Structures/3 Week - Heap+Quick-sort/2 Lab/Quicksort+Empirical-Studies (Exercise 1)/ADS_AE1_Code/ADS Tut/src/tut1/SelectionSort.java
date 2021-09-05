package tut1;

import java.util.Arrays;

public class SelectionSort {
	public static int[] selectionSort(int[] nums) {
		for (int i=0; i < nums.length-1; i++) {
			int index = i;
			for (int j=i+1; j < nums.length; j++) {
				if (nums[j] < nums[index]) {
					index = j;
				}
			}
			int min = nums[index];
			nums[index] = nums[i];
			nums[index] = min;
		}
		return nums;
	}
	
	public static void main(String[] args) {
		int[] a = {5,2,4,6,1,3};
		System.out.println(Arrays.toString(selectionSort((a))));
	}
}

