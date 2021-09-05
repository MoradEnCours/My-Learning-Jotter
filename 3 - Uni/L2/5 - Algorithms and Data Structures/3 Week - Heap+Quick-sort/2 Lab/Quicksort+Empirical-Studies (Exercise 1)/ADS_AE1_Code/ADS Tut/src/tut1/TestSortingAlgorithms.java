package tut1;

public class TestSortingAlgorithms {
	
	/*
	 * Dans le loop on utilise length-1 pq sinon on aura OOB erreur
	 * Complexité = O(n)
	 */
	public static boolean isSorted(int[] nums) {
		for (int i=0; i < nums.length-1; i++) {
			int l = nums[i];
			int m = nums[i+1];
			if (l > m) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,0};
		System.out.println(isSorted(a));
	}
}
