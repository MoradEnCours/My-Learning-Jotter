package dll;

public class Node {

	int data;
	Node previous;
	Node next;
	
	/*
	 * Constructor for when given int data but no previous or next nodes features
	 *   so set to null as default.
	 */
	public Node(int data) {
		this.data = data;
		this.previous = null;
		this.next = null;
	}
	
	
	/*
	 * Constructor for when given int data and previous and next nodes to include in
	 *   place of a default null value.
	 */
	public Node(int data, Node previous, Node next) {
		this.data = data;
		this.previous = previous;
		this.next = next;
	}
	
	/* Getter method returning integer data value */
	public int getData() {
		return data;
	}
	
	
	/* Setter method as a complement to the getter method */ 
	public void setData(int data) {
		this.data = data;
	}
	
	/* Getter method for previous node */
	public Node getPrevious() {
		return previous;
	}
	
	
	/* Setter method for previous node */
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	
	/* Getter method for next node */
	public Node getNext() {
		return next;
	}
	
	
	/* Setter method for next node */
	public void setNext(Node next) {
		this.next = next;
	}
}
