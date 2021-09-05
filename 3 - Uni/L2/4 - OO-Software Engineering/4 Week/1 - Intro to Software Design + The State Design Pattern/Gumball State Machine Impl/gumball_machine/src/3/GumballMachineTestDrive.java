package state.gumballstatewinner;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachine gumballMachine = 
			new GumballMachine(10);

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
