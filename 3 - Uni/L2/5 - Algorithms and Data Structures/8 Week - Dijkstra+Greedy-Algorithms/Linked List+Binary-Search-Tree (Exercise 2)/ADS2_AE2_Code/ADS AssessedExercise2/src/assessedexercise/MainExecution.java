package assessedexercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import dll.DoublyLinkedList;
import bst.BinarySearchTree;



public class MainExecution {
	
	/* Additional class used initially for testing code a bit and later for carrying
	 *   out the empirical study.*/
	
	public static DoublyLinkedList list1 = new DoublyLinkedList();
	public static DoublyLinkedList list2 = new DoublyLinkedList();
	public static DoublyLinkedList list3 = new DoublyLinkedList();
	public static DoublyLinkedList emptyList = new DoublyLinkedList();
	
	public static BinarySearchTree tree1 = new BinarySearchTree();
	public static BinarySearchTree tree2 = new BinarySearchTree();
	public static BinarySearchTree tree3 = new BinarySearchTree();
	public static BinarySearchTree emptyTree = new BinarySearchTree();
	

	public static void populateDataDll() {
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(7);
		
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list1.printInorder(); 
		System.out.println("- " + list1.getSize());
		list2.printInorder(); 
		System.out.println("- " + list2.getSize());
	}
	
	
	public static void populateDataBST() {
		tree1.add(1);
		tree1.add(4);
		tree1.add(3);
		tree1.add(8);

		tree2.add(5);
		tree2.add(7);
		tree2.add(2);
		tree2.add(1);
		tree1.printInorder(); System.out.println("- " + tree1.getSize());
		tree2.printInorder(); System.out.println("- " + tree2.getSize());
	}
	
	private static void deleteBST() {
		tree2.deleteKey(5);
		tree2.deleteKey(7);
		tree2.printInorder();
		System.out.println("- " + tree2.getSize());
	}
	
	private static void deleteDll() {
		list2.deleteElement(3);
		list2.deleteElement(4);
		list2.printInorder();
		System.out.println("- " + list2.getSize());
	}
	
	private static void differenceDll() {
		list3 = list3.dllDifference(list1, list2);
		list3.printInorder();
		System.out.println("- " + list3.getSize());
	}

	
	private static void differenceBST() {
		tree3 = tree3.treesDiffer(tree1, tree2);
		tree3.printInorder();
		System.out.println("- " + tree3.getSize());
	}
	
		
	public static void hasElementDll(DoublyLinkedList list, int element) {
		System.out.println("Has element? " + list.hasElement(element));
	}

	
	public static void hasElementBST(BinarySearchTree tree, int element) {
		System.out.println("Has element? " + tree.hasElement(element));
	}
	
	
	private static void isEmptyDll(DoublyLinkedList list) {
		System.out.println("Is it empty? " + list.isEmpty());
	}
	
	
	private static void isEmptyBST(BinarySearchTree tree) {
		System.out.println("Is it empty? " + tree.isEmpty());
	}
	
	
	private static void DllSize(DoublyLinkedList list) {
		System.out.println("List size: " + list.getSize());
	}
	
	
	private static void BSTSize(BinarySearchTree tree) {
		System.out.println("Tree size: " + tree.getSize());
	}
	
	
	private static void unionDll(DoublyLinkedList list1, DoublyLinkedList list2) {
		list3 = list3.dllUnion(list1, list2);
		list3.printInorder();
		System.out.println("- " + list3.getSize());
	}
	
	
	private static void intersectionDLLTest() {
		list3 = list3.dllIntersection(list1, list2);
		list3.printInorder();
		System.out.println("- " + list3.getSize());
	}

	
	private static void unionBST(BinarySearchTree tree1, BinarySearchTree tree2) {
		tree3 = tree3.unifyTrees(tree1, tree2);
		tree3.printInorder();
		System.out.println("- " + tree3.getSize());
	}
	
	
	private static void intersectionBST() {
		tree3 = tree3.intersectTrees(tree1, tree2);
		tree3.printInorder();
		System.out.println("- " + tree3.getSize());
	}
	
	
	/* Reading in the file.*/
	private static int[] readFile(String filePath) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		try (Scanner s = new Scanner(new File(filePath))) {
			while (s.hasNext()) {
				int parsedInt = Integer.parseInt(s.nextLine());
				arrayList.add(parsedInt);
			}
		} catch (FileNotFoundException e) {
			// Deal with the possible exception
		}
		int[] intArray = new int[arrayList.size()];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = arrayList.get(i).intValue();
		}
		return intArray;
	}	
	
	
	/*
	 * Compares the two implementations of the Dynamic Set ADT by carrying out the 
	 *   following empirical study. First, populates (an initially empty) set S 
	 *   with all the elements from dataset int20k.txt. Then, generates 100 random
	 *   numbers in the interval [0, 49999].
	 *   Finally, for each random number x records the time taken to execute 
	 *   IS-ELEMENT(S,x). Gives the average running time of IS-ELEMENT over 100 
	 *   calls in the two implementations of the ADT.
	 */
	private static void empiricalStudy() {
		int[] intArray = readFile(System.getProperty("user.dir")+ "\\Files\\" + "int20k" +".txt");
		long computedBSTTime = 0;
		long computedDllTime = 0;
	    Random random = new Random();
		for(int number : intArray) {
			tree1.add(number);
			list1.add(number);
		}
		long startTime = 0, endTime = 0;
		for (int i = 0; i < 100; i++)
	    {
			int number = random.nextInt(50000);
			System.out.println(number);
	        startTime = System.nanoTime();
	        list1.hasElement(number);
	        endTime = System.nanoTime();
	        computedDllTime += (endTime - startTime)/100;
	        
	        startTime = System.nanoTime();
	        tree1.hasElement(number);
	        endTime = System.nanoTime();
	        computedBSTTime += (endTime - startTime)/100;
	    }
		System.out.println("Average time of BST IS-ELEMENT: " + computedBSTTime);
		System.out.println("Average time of DLL IS-ELEMENT: " + computedDllTime);
		/* The output of SET-SIZE(S)*/
		System.out.println("Output of SET-SIZE(S): "+tree1.getSize());
		//System.out.println("Set size of DLL: "+list1.getSize());
		/* The height of the BST implementing set S.*/
		System.out.println("Height of BST implementing set S: "+tree1.maximumTreeDepth());
	}
	
	public static void main(String... args) {
		empiricalStudy();

		//populateDataBST();
		//deleteBST();
		//differenceBST();
		//hasElementBST(tree1, 1); hasElementBST(tree1, 100);
		//isEmptyBST(emptyTree); isEmptyBST(tree1);
		//BSTSize(tree1);
		//intersectionBST();
		//unionBST(tree1, tree2);
		
		//populateDataDll();
		//differenceDll(); 
		//deleteDll();
		//isEmptyDll(list1); isEmptyDll(emptyList);
		//hasElementDll(list1, 4); hasElementDll(list1, 10);
		//DllSize(list1); testDllSize(list3);
		//unionDll(list1, list2);
		//list1.printInorder(); list2.printInorder();
		//intersectionDLLTest();
		
	}
}
