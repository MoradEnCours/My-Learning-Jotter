package tut2;

public class Fact {
	public static int fact(int n) {
		assert (n > 0);
		if (n == 1)
			return 1;
		else
			return n * fact(n - 1);
	}
	
	
	public static void main(String[] args) {
		System.out.println(fact(3));
		
	}
}
