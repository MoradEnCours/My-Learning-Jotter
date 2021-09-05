package tut2;

public class FIB {

	public static int fib(int n) {
		assert (n >= 0);
		if (n <= 1)
			return n;
		else
			return fib(n-1) + fib(n-2);
	}
	
	public static void main(String[] args) {
		System.out.println(fib(10));
	}
}
