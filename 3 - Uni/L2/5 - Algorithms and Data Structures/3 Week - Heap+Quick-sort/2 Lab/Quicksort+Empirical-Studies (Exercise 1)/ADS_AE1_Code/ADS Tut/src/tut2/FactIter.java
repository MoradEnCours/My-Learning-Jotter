package tut2;

public class FactIter {
	/*
	 * Complexity of  O(n)
	 */
	public static int factIter(int n) {
		assert (n > 0);
		int res = 1;
		for (int j=1; j <= n; j++) {
			res *= j;
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(factIter(3));
	}
}
