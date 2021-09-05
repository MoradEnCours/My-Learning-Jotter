package immutable;

import java.util.ArrayList;
import java.util.List;

public final class Person {
	private final List<String> names;

	public Person (List<String> names) {
		this.names = new ArrayList<>(names);
	}

	/**
	 * @return the names
	 */
	public List<String> getNames() {
		return new ArrayList<>(names);
	}

	@Override
	public String toString() {
		return String.join(" ", names);
	}
	
	public static void main(String[] args) {
		List<String> myNames = new ArrayList<>();
		myNames.add("Mary");
		myNames.add("Ellen");
		myNames.add("Foster");
		Person me = new Person(myNames);
		
		System.out.println(me);
		
		myNames.add("Petrick");
	
		System.out.println(me);

	}
	
}
