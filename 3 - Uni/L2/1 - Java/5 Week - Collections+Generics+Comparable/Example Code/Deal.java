package uk.ac.glasgow.jp2;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Based on https://docs.oracle.com/javase/tutorial/collections/interfaces/list.html

public class Deal {
	public static void main(String[] args) {
		int numHands = 4;
		int cardsPerHand = 7;

		// Make a normal 52-card deck.
		String[] suit = new String[] { "spades", "hearts", "diamonds", "clubs" };
		String[] rank = new String[] { "ace", "2", "3", "4", "5", "6", "7",
				"8", "9", "10", "jack", "queen", "king" };
		
		List<String> suitList = Arrays.asList("spades", "hearts", "diamonds", "clubs");
		
		List<String> deck = new LinkedList<>();
		for (String s : suitList) {
			for (String r : rank) {
				deck.add(r + " of " + s);
			}
		}
		
		System.out.println(deck);
		
		// Shuffle the deck.
		Collections.shuffle(deck);

		if (numHands * cardsPerHand > deck.size()) {
			System.out.println("Not enough cards.");
			return;
		}

		for (int i = 0; i < numHands; i++) {
			System.out.println("Hand " + i + ": " + deck.subList(i*cardsPerHand,
					i*cardsPerHand + cardsPerHand));
		}
	}
}