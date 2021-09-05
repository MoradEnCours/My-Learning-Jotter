package state.gumballstate;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(2);

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		
		gumballMachine.refill(5);
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
