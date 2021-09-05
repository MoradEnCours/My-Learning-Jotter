package state.gumballstatewinner;

public interface State {
 
	public void insertCoin();
	public void ejectCoin();
	public void turnCrank();
	public void dispense();
	
	public void refill();
}
