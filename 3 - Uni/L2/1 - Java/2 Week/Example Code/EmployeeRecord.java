public class EmployeeRecord {
	private String name;
	private int age;
	
	public void getOlder() {
		this.age = this.age + 1;
	}
	
	public void sayName() {
		System.out.println("Hello, my name is " + this.name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public EmployeeRecord(String n, int a) {
		name = n;
		age = a;
	}
}