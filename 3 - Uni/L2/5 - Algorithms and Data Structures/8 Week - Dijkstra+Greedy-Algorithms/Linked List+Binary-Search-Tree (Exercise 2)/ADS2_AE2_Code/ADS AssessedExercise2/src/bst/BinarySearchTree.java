package bst;

public class BinarySearchTree {
	
	Node root;
	
	
	/* Construct the Binary Search Tree */
	public BinarySearchTree() {
		this.root = null;
	}

	
	/* Helper function for adding to the tree.
	 * Adding: Recurring down the tree, smaller elements added to the left, bigger ones to the right.
	 * If the element already exists,  return it (neither left nor right sides being
	 *   investigated so seems element is already there).
	 *   
	 * Complexity: In general, with binary search trees is affected by the height of
	 *   the tree,with time complexity O(h) [h for heght). With worst case it's O(n)
	 *   for the add operation.
	 *   
	 */
	private Node recursiveAdd(Node current, int key) {
		/* Return a new key if the tree is empty. */
		if (current == null) {
			return new Node(key);
		} else if (key < current.key) {
			current.left = recursiveAdd(current.left, key);
		} else if (key > current.key) {
			current.right = recursiveAdd(current.right, key);
		} else {
			return current;
		}
		return current;
	}
		

	/* Add node to the tree from the root.*/
	public void add(int element) {
		this.root = recursiveAdd(root, element);
	}
	
	
	/* Return minimum value of node; to be located by going left, and break
	 *   out of the while loop once given a null (no more smaller elements, end reached).
	 .*/
	int minimumValue(Node node) {
		int minValue = node.key;
		while (node.left != null) {
			minValue = node.left.key;
			node = node.left;
		}
		return minValue;
	}
	

	/*
	 * If the tree is empty, simply return the node - Base case
	 * If not null, recur left and recur right.
	 * 
	 * Complexity: Worst case of O(n)
	 */
	private Node recursiveDelete(Node node, int key) {
		if (node == null)
			return node;
		if (key < node.key)
			node.left = recursiveDelete(node.left, key);
		else if (key > node.key)
			node.right = recursiveDelete(node.right, key);
		/*
		 * If the key and root's key are the same, this is the node to delete then.
		 * Furthermore, conditional checks take into account if node has one child
		 *   or none at all.  
		 * Also, for node having two children, we get the smallest value in the right
		 *   subtree (inorder successor). Finally, that inorder successor is deleted
		 *   and node returned finally.
		 */
		else {
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			node.key = minimumValue(node.right);
			node.right = recursiveDelete(node.right, node.key);
		}
		
		return node;
	}
	
	
	/* Primary method for deleting key element, making use of recursiveDelete. */
	public void deleteKey(int key) {
		this.root = recursiveDelete(this.root, key);
	}
	
	
	/* Checks if given element exists in the tree.
	 * 
	 * Complexity: Worst case of O(n)
	 */
	public boolean hasElement(int element) {
		return recursiveHasElement(this.root, element);
	}
	
	
	/* Helper method with tree traversal for hasElement */ 
	private boolean recursiveHasElement(Node node, int value) {
		/* Check to see if the tree is empty. No tree, no elements so false: can't have element if empty.*/
		if (node == null)
			return false;
		/* Check to see if node has value similar to what's being looked for.
		 * Recur left: return true if element found. If element not found,
		 * recur right: return true if element found, otherwise false: element doesn't exist.
		 /*/
		if (node.key == value)
			return true;
		if (recursiveHasElement(node.left, value))
			return true;
		
		return recursiveHasElement(node.right, value);
	}
	
	
	/* Gives the tree size, excluding in its count the root node.
	 * If the root's null, size is just 0 so return it.
	 * If size isn't 0, increment the size recurring left and right. No increments
	 *   if going left a null value is arrived at; same for recurring right and landing
	 *   on a null. 
	 * After completing traversing left and right, return the final size of the tree.
	 */
	private int recursiveGetSize(Node root) {
		if (root == null)
			return 0;
		int size = 0;
		if (root.left != null)
			size++;
		if (root.right != null)
			size++;
		
		size += (recursiveGetSize(root.left) + recursiveGetSize(root.right));
		return size;
	}
	
	
	/* Checks if tree is empty.
	 * 
	 * Complexity: O(n)
	 */
	public boolean isEmpty() {
		int treeSize = getSize();
		if (treeSize == 0)
			return true;
		return false;
		// return treeSize == 0;		Shorthand form
	}
	
	
	/* Gets the tree size. If the tree's root is null, returns 0;
	 *   else, include the root as well in getting the size of the tree.
	 * 
	 * Complexity: Worst case O(n)
	 */
	public int getSize() {
		// adds root node
		if (root == null)
			return 0;
		else
			return recursiveGetSize(root) + 1;
	}
	
	
	/* Eecursive intersection between two trees.
	 * 
	 * Complexity: O(n+m)
	 */
	private void recursiveIntersection(BinarySearchTree tree1, Node node2, BinarySearchTree tree3) {
		/* No common nodes, hence return (can't have an intersection if root of a tree has nothing).*/
		if (node2 == null) return;	
		/* Recur on left, recur on right.*/
		recursiveIntersection(tree1, node2.left, tree3);
		recursiveIntersection(tree1, node2.right, tree3);
		/* Dealing with the node: if there's a common element (of intsersection), add it to tree3.*/
		if (tree1.hasElement(node2.key))
			tree3.add(node2.key);
	}
	
	
	/* Make a third tree with intersection results, calling recursive helper method
	 *   on tree1 and tree2, finally returning tree3 with results for intersection.. 
	 * 
	 * Complexity: O(m+n)
	 */
	public BinarySearchTree intersectTrees(BinarySearchTree tree1, BinarySearchTree tree2) {
		BinarySearchTree tree3 = new BinarySearchTree();
		/* Add to tree3 the intersection of trees1&2.*/
		recursiveIntersection(tree1, tree2.root, tree3);
		/* Add to tree3 the intersection of trees2&1.*/
		recursiveIntersection(tree2, tree1.root, tree3);
		return tree3;
	}


	/* Helper method for difference of sets.*/
	private void recursiveDifference(BinarySearchTree tree1, Node node2, BinarySearchTree tree3) {
		/* Nothing to add, just return.*/
		if (node2 == null) return;
		/* Recur on left, recur on right.*/
		recursiveDifference(tree1, node2.left, tree3);
		recursiveDifference(tree1, node2.right, tree3);
		/* Dealing with the node: if tree doesn't have the key element in the
		 *   node, add the node's key to tree3.
		 */
		if (!tree1.hasElement(node2.key))
			tree3.add(node2.key);
	}
	
	
	/* Make a third tree with difference results returned in it
	 * Add what's in tree1 but not in tree2 and what's in tree2 but not in tree1. 
	 * 
	 * Complexity: O(n+m)
	 * */
	public BinarySearchTree treesDiffer(BinarySearchTree tree1, BinarySearchTree tree2) {
		BinarySearchTree tree3 = new BinarySearchTree();
		/* Add to tree3 the difference of trees1-2.*/ 
		recursiveDifference(tree1, tree2.root, tree3);
		/* Add to tree3 the difference of trees2-1.*/
		recursiveDifference(tree2, tree1.root, tree3);
		return tree3;
	}
	

	/* Helper method for checking subset.*/
	public boolean recursiveIsSubset(Node node1, BinarySearchTree tree2, boolean exitRecursion) {
		/* The empty set is a subset of every set.*/
		if (node1 == null) return true;
		/* Check if subset tree is part of larger tree; break recursion if an element isn't.*/
		if (!tree2.hasElement(node1.key))
			return false;
		/* Recurring left: Exit recursion if there exists an element not in the larger tree.*/ 
		if (!recursiveIsSubset(node1.left, tree2, true))
			return false;
		/* Recurring right: If element not in larger tree, quit the recursion.*/
		if (!recursiveIsSubset(node1.right, tree2, true))
			return false;
		/* If recursion didn't exit because of subset element not existing in bigger
		 *   set,is a subset; otherwise, it isn't.
		 */
		if (!exitRecursion)
			return false;
		else 
			return true;	
		// return !exitRecursion;		Shorthand form
	}
	
	/* Checks subset or not.
	 * 
	 * Complexity: [O(m*n) where m and n are number of nodes in given two trees]
	 */
	public boolean isSubset(BinarySearchTree tree2) {
		return recursiveIsSubset(this.root, tree2, true);
	}
	
	/* Helper recursive method for unifying trees, adding contents of tree2 to tree3. */
	private void recursiveUnion(BinarySearchTree tree3, Node node) {
		if (node == null)	return;
		/* Recur on left subtree, recur on right subtree.*/
		recursiveUnion(tree3, node.left);
		recursiveUnion(tree3, node.right);
		/* Dealing with the node.*/
		tree3.add(node.key);
	}
	
	
	/* Unification of trees.
	 * Assign a third tree values of tree 1.
	 * For all union values to be included in tree3, call recursiveUnion to add
	 *   items from tree2 and finally return tree3 containing the union of tree1&2. 
	 * 
	 * Complexity: O(n+m)
	 * */
	public BinarySearchTree unifyTrees(BinarySearchTree tree1, BinarySearchTree tree2) {
		BinarySearchTree tree3 = tree1;
		recursiveUnion(tree3, tree2.root);
		return tree3;
	}

	
	// A utility function to do in-order traversal of BST
	/*
	 * Helper function to traverse in-order the binary search tree.
	 * Recurs left, recur right, printing the values.
	 */
	void recursiveInOrderPrint(Node root) {
		if (root != null) {
			recursiveInOrderPrint(root.left);
			System.out.print(root.key + " ");
			recursiveInOrderPrint(root.right);
		}
	}
	
	
	/* Makes call to recursive helper function to print in order.*/
	public void printInorder() {
		recursiveInOrderPrint(root);
	}
	
	
	/* Method for finding the maximum height of the tree.*/
	public int maximumTreeDepth() {
		return recursiveMaximumTreeDepth(this.root)-1;
	}
	
	/* Helper recursive method for finding tree height.
	 * If root is null, depth is simply 0 then.
	 * Otherwise, recur left and right, incrementing the sum count respectively.
	 * Comparing sum of left and right, if left is greater then add root to its part,
	 *   else include it in the right.
	 */
	private int recursiveMaximumTreeDepth(Node root) {
		if(root == null) {
			return 0;
		}
		int sumOfLeft = recursiveMaximumTreeDepth(root.left);
		int sumOfRight = recursiveMaximumTreeDepth(root.right);
		
		if(sumOfLeft > sumOfRight) 
			return (sumOfLeft + 1);
		else
			return (sumOfRight + 1);
	}
	
	
}
