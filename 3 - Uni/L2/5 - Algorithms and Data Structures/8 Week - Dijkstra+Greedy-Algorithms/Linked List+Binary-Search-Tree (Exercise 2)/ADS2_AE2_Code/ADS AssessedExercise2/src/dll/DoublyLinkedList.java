package dll;

public class DoublyLinkedList {

	Node head;
	Node tail;
	int size;
	
	/* Constructor with size set to 0 */
	public DoublyLinkedList() {
		this.size = 0;
	}
	
	
	/*
	 * Adds the given element.
	 * First check that the element does not exist already or head value is null:
	 *   if this condition evaluates to false, skip the block and return false - nothing added.
	 * If boolean evaluation is true, construct a new node with the given element value.
	 * Set the next node to have the value of the head and the previous to null.
	 * If the head isn't null, set the new node in the head's previous.
	 * If the tail is null, assign the value of the new node.
	 * Put new node as head and increment size counter for newly added element; finally, return true.
	 * Complexity: O(1) for the insertion but in traversing the list to see if the
	 *   element already exists, without directly knowing which node to search to make
	 *   the insertion, requires complexity O(n).
	 *   This arises due to the linear nature of the search, searching from the beginning
	 *   until the element is found to exist or not. In the case of adding straight to the head or
	 *   tail of the list, complexity would also be O(1).
	 * 
	 */
	public boolean add(int element) {
		if (!hasElement(element) || head == null) {
			Node newNode = new Node(element);
			newNode.next = head;
			newNode.previous = null;
			if (head != null)
				head.previous = newNode;
			if (tail == null)
				tail = newNode;
			head = newNode;
			size++;
			return true;
		}
		return false;
	}
	
	
	/* Removes the specified element if it's present 
	 * Run a while loop as long as creater node named iterator isn't null.
	 * If current head value of node doesn't match element to be deleted;
	 *   move iterator to check the next node. Continue until the element to be deleted
	 *   is found. If node containing value to be deleted is found, delete it and
	 *   decrement the size as a result. Return true to represent deletion was successful.
	 *  If iterator reaches end without finding element to delete, it's null so
	 *    while loop breaks. Return false to represent unsuccessful deletion. 
	 *  Complexity: Without a node provided to reference where to find the element to
	 *    delete, the iterator needs to search through the list to find the arbitrary value
	 *    and doing so would require complexity O(n). (Knowing the node starting off
	 *    would make for deletion of complexity O(1) and in the case of deleting straight from the head or
	 *    tail of the list, complexity would also be O(1)).
	 */
	public boolean deleteElement(int element) {
		Node iterator = this.head;
		while (iterator != null) {
			if(iterator.data == element) {
				deleteNode(iterator);
				this.size--;
				return true;
			}
			iterator = iterator.next;
		}
		return false;
	}
	
	
	/* Helper method for deleteElement, deletes the node.*/
	public Node deleteNode(Node nodeToDelete) {
		/* If either the head or the node to be deleted is null, return null (base case) */
		if (this.head == null || nodeToDelete == null)
			return null;
		
		/* If node to be deleted equals the head, set the head with the value of the node 
		 *   which is after the node to be deleted.
		 */
		if (this.head == nodeToDelete)
			head = nodeToDelete.next;
		
		/* If the node to be deleted is not the last node, it is okay to change ]
		 *   the next node.
		 * If the node to be deleted is not the first node, it is okay to change
		 *   the previous node.
		 */
		if (nodeToDelete.next != null)
			nodeToDelete.next.previous = nodeToDelete.previous;
		if (nodeToDelete.previous != null)
			nodeToDelete.previous.next = nodeToDelete.next;
		/* Nullify node.*/
		nodeToDelete = null;
		/* Head was assigned earlier with the one after the node to be deleted
		 *   so return head as method's conclusion.
		 */
		return head;
		
	}
	
	/* 
	 * Create an iterator node including head value and set up a while loop:
	 *   Continue looping as long as the iterator does not equal to null.
	 *   Conditionally check if the retrieved integer data value matches the element value.
	 *   Return true if conditional check evaluates to true. Otherwise, continue getting next element.
	 *   If while loop ends, reaching null,  means element is not present so return false.
	 *   Complexity: O(n)
	 */
	public boolean hasElement(int element) {
		Node iterator = this.head;
		while (iterator != null) {
			if (iterator.getData() == element)
				return true;
			iterator = iterator.getNext();
		}
		return false;
	}
	
	
	/* Checks whether or not the set is empty 
	 * Complexity: O(1)
	 */
	public boolean isEmpty() {
		if (this.size == 0)
			return true;
		else
			return false;
		//return this.size == 0; shortcut of above.
	}
	
	
	/* Returns the number of elements in the set 
	 * Complexity: O(1)
	 */
	public int getSize() {
		return this.size;
	}
	
	
	/* Returns the union of two sets
	 * Starting off, initialise nodes for doubly linked list1 and list2 heads.
	 * Construct a new doubly linked list to represent unification of elements between
	 *   the two dll's to be unified.
	 * FIrst while loop: Add to the new unifying linked list1 head data value of node.
	 *   Continue adding till the loop breaks - null so no more items to add to unifying list.
	 *  Second while loop: Similar to the first one but do it with the second list.
	 *  From previous add method, if list2 tries adding an element which list1 has 
	 *    already added to the unifying list, it gets patched. 
	 * Complexity: O(m+n) [m and n representing the two lists to be unified respectively]
	 */	
	public DoublyLinkedList dllUnion(DoublyLinkedList list1, DoublyLinkedList list2) {
		Node list1Head = list1.head;
		Node list2Head = list2.head;
		DoublyLinkedList unifiedDll = new DoublyLinkedList();
		while (list1Head != null) {
			unifiedDll.add(list1Head.getData());
			list1Head = list1Head.getNext();
		}
		while (list2Head != null) {
			unifiedDll.add(list2Head.getData());
			list2Head = list2Head.getNext();
		}
		return unifiedDll;
	}
	
	
	/* Returns the intersection of two sets 
	 * Starting off, initialise nodes for doubly linked list1 and list2 heads.
	 * Construct a new doubly linked list to represent intersection of elements between
	 *   the two dll's to be intersected.
	 * Run nested while loops and match elements contained in list1 that also exist
	 *   in list2; add matching elements to the list representing the common elements.
	 *   Simply check for each item in list1 whether any the items in list2 match and
	 *   continue moving along in list2 until null is reached to end loop and then move
	 *   along list1 to check the next item and compare similar to before, and continue
	 *   until both lists have been completely traversed to find intersecting elements.
	 * Once traversal stops due to reaching nul, return the list with intersections.
	 *  
	 * Complexity: O(m*n) [m and n representing the two lists to be unified respectively]
	 */
	public DoublyLinkedList dllIntersection(DoublyLinkedList list1, DoublyLinkedList list2) {
		Node list1Head = list1.head;
		Node list2Head = list2.head;
		DoublyLinkedList intersectedDll = new DoublyLinkedList();
		while (list1Head != null) {
			while (list2Head != null) {
				if (list1Head.getData() == list2Head.getData())
					intersectedDll.add(list2Head.getData());
				list2Head = list2Head.getNext();
			}
			list1Head = list1Head.getNext();
		}
		return intersectedDll;
	}

	
	/* Returns the difference of two sets 
	 * Starting off, initialise nodes for doubly linked list1 and list2 heads.
	 * Construct a new doubly linked list to represent set difference of elements between
	 *   the two dll's being differed.
	 * For more clarity, the tmp variables are used. In tmp1 is stored data of
	 *   node for list1 head value, tmp2 server a similar purpose for list2.
	 *   In the separate while loops, the first one checks if the element under 
	 *   investigation isn't contained in list2, and if this is true, the element is
	 *   added to the dll containing the different elements of list1 not found in list2.
	 *   First while loop terminates once the end is reached and nul breaks the first
	 *   while loop. Second while loop does practically the same sort of thing but
	 *   adding elements contained in list2 not found in list1.
	 * The dll representing differences is returned in the end.
	 * 
	 * Complexity: O(m+n)
	 */
	public DoublyLinkedList dllDifference(DoublyLinkedList list1, DoublyLinkedList list2) {
		Node list1Head = list1.head;
		Node list2Head = list2.head;
		DoublyLinkedList differenceDll = new DoublyLinkedList();
		while (list1Head != null) {
			int tmp1 = list1Head.getData();
			if (!list2.hasElement(tmp1))
				differenceDll.add(tmp1);
			list1Head = list1Head.getNext();
		}
		
		while (list2Head != null) {
			int tmp2 = list2Head.getData();
			if (!list1.hasElement(tmp2))
				differenceDll.add(tmp2);
			list2Head = list2Head.getNext();
		}
		
		return differenceDll;
	}
	
	
	/* Checks if a set is a subset of another set 
	 * Basically a subset of the bigger set would arrive at the end of the while loop
	 *   without evaluating in the conditional check to true which would return value false,
	 *   as it means there is an element which the bigger set doesn't contain. So if
	 *   all element values checked in the subset are in the bigger set, with no more
	 *   checks to make, the conclusion is that this is a subset so return true.
	 * Complexity: O(m+n)
	 */
	public boolean isSubset(DoublyLinkedList list1) {
		Node list2Head = this.head;
		while (list2Head != null) {
			if (!list1.hasElement(list2Head.getData()))
				return false;
			list2Head = list2Head.getNext();
		}
		return true;
	}
	
	
	/* Extra method for printing in order.*/
	public void printInorder() {
		Node iterator = head;
		while (iterator != null) {
			System.out.print(iterator.data+" ");
			iterator = iterator.getNext();
		}
	}
}
