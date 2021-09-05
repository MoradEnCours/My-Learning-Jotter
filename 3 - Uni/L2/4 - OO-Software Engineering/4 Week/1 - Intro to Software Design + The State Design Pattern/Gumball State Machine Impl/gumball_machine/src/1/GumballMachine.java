package state.gumball;

public class GumballMachine {
 
	final static int SOLD_OUT = 0;
	final static int NO_COIN = 1;
	final static int HAS_COIN = 2;
	final static int SOLD = 3;
 
	int state = SOLD_OUT;
	int count = 0;
  
	public GumballMachine(int count) {
		this.count = count;
		if (count > 0) {
			state = NO_COIN;
		}
	}
  
	public void insertCoin() {
		if (state == HAS_COIN) {
			System.out.println("You can't insert another coin");
		} else if (state == NO_COIN) {
			state = HAS_COIN;
			System.out.println("You inserted a coin");
		} else if (state == SOLD_OUT) {
			System.out.println("You can't insert a coin, the machine is sold out");
		} else if (state == SOLD) {
        	System.out.println("Please wait, we're already giving you a gumball");
		}
	}

	public void ejectCoin() {
		if (state == HAS_COIN) {
			System.out.println("Coin returned");
			state = NO_COIN;
		} else if (state == NO_COIN) {
			System.out.println("You haven't inserted a coin");
		} else if (state == SOLD) {
			System.out.println("Sorry, you already turned the crank");
		} else if (state == SOLD_OUT) {
        	System.out.println("You can't eject, you haven't inserted a coin yet");
		}
	}
 
	public void turnCrank() {
		if (state == SOLD) {
			System.out.println("Turning twice doesn't get you another gumball!");
		} else if (state == NO_COIN) {
			System.out.println("You turned but there's no coin");
		} else if (state == SOLD_OUT) {
			System.out.println("You turned, but there are no gumballs");
		} else if (state == HAS_COIN) {
			System.out.println("You turned...");
			state = SOLD;
			dispense();
		}
	}
 
	private void dispense() {
		if (state == SOLD) {
			System.out.println("A gumball comes rolling out the slot");
			count = count - 1;
			if (count == 0) {
				System.out.println("Oops, out of gumballs!");
				state = SOLD_OUT;
			} else {
				state = NO_COIN;
			}
		} else if (state == NO_COIN) {
			System.out.println("You need to pay first");
		} else if (state == SOLD_OUT) {
			System.out.println("No gumball dispensed");
		} else if (state == HAS_COIN) {
			System.out.println("No gumball dispensed");
		}
	}
 
	public void refill(int numGumBalls) {
		this.count = numGumBalls;
		state = NO_COIN;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004\n");
		result.append("Inventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\nMachine is ");
		if (state == SOLD_OUT) {
			result.append("sold out");
		} else if (state == NO_COIN) {
			result.append("waiting for coin");
		} else if (state == HAS_COIN) {
			result.append("waiting for turn of crank");
		} else if (state == SOLD) {
			result.append("delivering a gumball");
		}
		result.append("\n");
		return result.toString();
	}
}


