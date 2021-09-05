package tut2;

public class FactTail {
	/*
	 * Complexity of  O(n)
	 */
	public static int factTail(int n, int acc) {
		assert (n > 0);
		assert (acc > 0);
		if (n == 1)
			return acc;
		else
			return factTail(n-1, n * acc);
	}
	
	public static void main(String[] args) {
		System.out.println(factTail(3, 1));
	}
	
}
