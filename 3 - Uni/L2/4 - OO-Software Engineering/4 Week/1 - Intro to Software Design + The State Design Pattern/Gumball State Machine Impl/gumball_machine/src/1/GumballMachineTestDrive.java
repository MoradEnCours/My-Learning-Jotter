package state.gumball;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(5);

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.ejectCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.ejectCoin();

		System.out.println(gumballMachine);

		gumballMachine.insertCoin();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();
		gumballMachine.insertCoin();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
