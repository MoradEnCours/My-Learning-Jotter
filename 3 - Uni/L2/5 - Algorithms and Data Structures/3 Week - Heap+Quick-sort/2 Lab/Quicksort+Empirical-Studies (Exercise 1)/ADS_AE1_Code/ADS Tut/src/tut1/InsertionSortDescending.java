package tut1;

import java.util.Arrays;

public class InsertionSortDescending {
	/*
	 * En revanche, changer > key contre < key 
	 */
	public static int[] insertionSortDescending(int[] nums) {
		for (int j=1; j < nums.length; j++) {
			int key = nums[j];
			int i = j-1;
			while (i >= 0 && nums[i] < key) {
				nums[i+1] =  nums[i];
				i -= 1;
			}
			nums[i+1] = key;
		}
		return nums;
	}
	
	public static void main(String[] args) {
		int[] a = {5,2,4,6,1,3};
		System.out.println(Arrays.toString(insertionSortDescending((a))));
	}
}
