package state.gumballstatewinner;

public class SoldOutState implements State {
    GumballMachine gumballMachine;
 
    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
 
	public void insertCoin() {
		System.out.println("You can't insert a coin, the machine is sold out");
	}
 
	public void ejectCoin() {
		System.out.println("You can't eject, you haven't inserted a coin yet");
	}
 
	public void turnCrank() {
		System.out.println("You turned, but there are no gumballs");
	}
 
	public void dispense() {
		System.out.println("No gumball dispensed");
	}
	
	public void refill() { 
		gumballMachine.setState(gumballMachine.getNoCoinState());
	}
 
	public String toString() {
		return "sold out";
	}
}
