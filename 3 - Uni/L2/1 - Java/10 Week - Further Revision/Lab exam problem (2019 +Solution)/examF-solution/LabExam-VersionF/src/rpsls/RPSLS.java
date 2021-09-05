package rpsls;

/**
 * A RPSLS game, including two players and a method to run the game and return the winner.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version F).
 * 
 * @author mefoster
 */
public class RPSLS {
	/** The players */
	private Player player1, player2;
	
	/**
	 * Creates a new RPSLS game with the two players.
	 * 
	 * @param player1 The first player
	 * @param player2 The second player
	 */
	public RPSLS(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Runs the RPSLS game until one of the players wins the given number of rounds.
	 * 
	 * @param numToWin How many wins a player needs to win the whole thing
	 * @return The winning player
	 */
	public Player playGame(int numToWin) {
		// Reset scores to zero at the start
		player1.setScore(0);
		player2.setScore(0);
		
		// Keep going until one player has won
		while (player1.getScore() < numToWin && player2.getScore() < numToWin) {
			Symbol symbol1 = player1.chooseSymbol();
			Symbol symbol2 = player2.chooseSymbol();
			if (symbol1 != symbol2) {
				if (beats(symbol1, symbol2)) {
					player1.setScore(player1.getScore() + 1);
				} else {
					player2.setScore(player2.getScore() + 1);
				}
			}
		}
		if (player1.getScore() >= numToWin) {
			return player1;
		} else {
			return player2;
		}
	}
	
	/**
	 * Helper method to check which symbol wins. This could also be included in the
	 * playGame() method above. Assumes that the two symbols are not the same.
	 * 
	 * @param symbol1 The first symbol
	 * @param symbol2 The second symbol
	 * @return true if the first symbol wins, and false if the second one wins
	 */
	private static boolean beats(Symbol symbol1, Symbol symbol2) {
		switch (symbol1) {
		case ROCK:
			return (symbol2 == Symbol.SCISSORS || symbol2 == Symbol.LIZARD);
			
		case PAPER:
			return (symbol2 == Symbol.ROCK || symbol2 == Symbol.SPOCK);
			
		case SCISSORS:
			return (symbol2 == Symbol.PAPER || symbol2 == Symbol.LIZARD);
			
		case LIZARD:
			return (symbol2 == Symbol.SPOCK || symbol2 == Symbol.PAPER);
			
		case SPOCK:
			return (symbol2 == Symbol.SCISSORS || symbol2 == Symbol.ROCK);
		}
		// Shouldn't happen
		return false;
	}

}
