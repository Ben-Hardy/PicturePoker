package game;

import java.util.ArrayList;

/**
 * A class representing a single Picture Poker Hand
 * @author benjaminhardy
 *
 */
public class Hand {
	/**
	 * hand - a Card array containing the hand's Cards. Will be 5 cards
	 */
	private Card[] hand;
	
	/**
	 * the Deck from which Cards will be drawn. Necessary because we both
	 * need to create the Hand and potentially swap out cards 
	 */
	private Deck deck = new Deck();
	
	/**
	 * Constructor function for Hand. Populates the hand array with 5 Cards
	 */
	public Hand() {
		
		hand = new Card[5];
		for (int i = 0; i < 5; i++) {
			hand[i] = deck.draw();
		}
	}
	
	/**
	 * Returns the Card at a given index from the hand array.
	 * @param index - the index of the card wanted
	 * @return the Card at the input index
	 */
	public Card cardAt(int index) {
		return hand[index];
	}
	
	/**
	 * Replaces the cards at each of the integer indices
	 * @param indices
	 */
	public void swapCards(ArrayList<Integer> indices) {
		for (Integer i: indices) {
			if (i < 5 && i >= 0) {
				hand[i] = deck.draw();	
			}
			
		}
	}
	
	/**
	 * testing function that replaces the Card at the given index with the
	 * provided Card
	 * @param newCard - the replacement Card
	 * @param index - the index you want to replace the Card at
	 */
	protected void swapAt(Card newCard, int index) {
		hand[index] = newCard;
	}
	
	@Override
	public String toString() {
		return hand[0].getPicture() + " " + hand[1].getPicture() +
				" " + hand[2].getPicture() + " " + hand[3].getPicture() + " "
				+ hand[4].getPicture();
	}
	
	/**
	 * Assesses the current hand array by looking at all 69 possible hands which
	 * 
	 * @return
	 */
	public int analyzeHand() {

		/* handCodes:
		 *  junk - 0
		 *  
		 *  1 pair: 6 choose 1 possible 1 pair hands
		 *  	cloud    - 1
		 *  	mushroom - 2
		 *      flower   - 3
		 *      luigi    - 4
		 *      mario    - 5
		 *      star     - 6
		 *  2 pairs: 6 choose 2 possible 2 pair hands
		 *      mushroom/cloud    - 7
		 *      flower/cloud      - 8
		 *      flower/mushroom   - 9
		 *      luigi/cloud       - 10
		 *      luigi/mushroom    - 11
		 *      luigi/flower      - 12
		 *      mario/cloud       - 13
		 *      mario/mushroom    - 14
		 *      mario/flower      - 15
		 *      mario/luigi       - 16
		 *      star/cloud        - 17
		 *      star/mushroom     - 18
		 *      star/flower       - 19
		 *      star/luigi        - 20
		 *      star/mario        - 21
		 *      
		 *  3 of a kind: 6 choose 1 possible 3 of a kind hands
		 *  	cloud             - 22
		 *      mushroom          - 23
		 *      flower            - 24
		 *      luigi             - 25
		 *      mario             - 26
		 *      star              - 27
		 *      
		 *  full house: format is (3 of first picture)/(2 of other picture)
		 *  (6 choose 2) * 2 possible hands 
		 *  	cloud/mushroom    - 28
		 *      mushroom/cloud    - 29
		 *      
		 *      cloud/flower      - 30
		 *      mushroom/flower   - 31
		 *      flower/cloud      - 32
		 *      flower/mushroom   - 33
		 *      
		 *      cloud/luigi       - 34
		 *      mushroom/luigi    - 35
		 *      flower/luigi      - 36
		 *      luigi/cloud       - 37
		 *      luigi/mushroom    - 38
		 *      luigi/flower      - 39
		 *      
		 *      cloud/mario       - 40
		 *      mushroom/mario    - 41
		 *      flower/mario      - 42
		 *      luigi/mario       - 43
		 *      mario/cloud       - 44
		 *      mario/mushroom    - 45
		 *      mario/flower      - 46
		 *      mario/luigi       - 47
		 *     
		 *      cloud/star        - 48
		 *      mushroom/star     - 49
		 *      flower/star       - 50
		 *      luigi/star        - 51
		 *      mario/star        - 52
		 *      star/cloud        - 53
		 *      star/mushroom     - 54
		 *      star/flower       - 55
		 *      star/luigi        - 56
		 *      star/mario        - 57
		 *      
		 *  4 of a kind: 6 choose 1 possible hands
		 *  	cloud             - 58
		 *  	mushroom          - 59
		 *  	flower            - 60
		 *  	luigi             - 61
		 *  	mario             - 62
		 *  	star              - 63
		 * 
		 *  5 of a kind: 6 choose 1 possible hands
		 *  	cloud             - 64
		 *  	mushroom          - 65
		 *  	flower            - 66
		 *  	luigi             - 67
		 *  	mario             - 68
		 *  	star              - 69
		 */
		int[] cardCounts = new int[6];
		
		for (Card card: hand) {
			cardCounts[card.getId()]++;
		}
		
		// these arrays keep track of which picture has what pairs, triples, etc.
		int[] pairs = new int[6];
		int[] threes = new int[6];
		int[] fours = new int[6];
		int[] fives = new int[6];
		
		// if numSingles is 5, then we have a junk hand
		int numSingles = 0;
		int numPairs = 0;
		Boolean hasThree = false;
		Boolean hasFour = false;
		Boolean hasFive = false;
		
		for (int i = 0; i < 6; i++) {
			if (cardCounts[i] == 5) {
				fives[i]++;
				hasFive = true;
			} else if (cardCounts[i] == 4) {
				fours[i]++;
				hasFour = true;
			} else if (cardCounts[i] == 3) {
				threes[i]++;
				hasThree = true;
			} else if (cardCounts[i] == 2) {
				pairs[i]++;
				numPairs++;
			} else if (cardCounts[i] == 1) {
				numSingles++;
			}
		}
		
		// detecting each possible hand. will do this in decending order
		// it's formatted kinda funny to make keeping track of each case easier
		
		// handling the case where we have 5 cards of the same picture
		if (hasFive) {
			if (fives[5] == 1)      { return 69; } 
			else if (fives[4] == 1) { return 68; } 
			else if (fives[3] == 1) { return 67; } 
			else if (fives[2] == 1) { return 66; } 
			else if (fives[1] == 1) { return 65; } 
			else if (fives[0] == 1) { return 64; }
		}
		
		// handling the case where four cards have the same picture
		if (hasFour) {
			if (fours[5] == 1)      { return 63; } 
			else if (fours[4] == 1) { return 62; } 
			else if (fours[3] == 1) { return 61; } 
			else if (fours[2] == 1) { return 60; } 
			else if (fours[1] == 1) { return 59; } 
			else if (fours[0] == 1) { return 58; }
		}
		
		// Full House combinations
		if (hasThree && numPairs == 1) {
			// star cases
			if (threes[5] == 1 && pairs[4] == 1)      { return 57; }
			else if (threes[5] == 1 && pairs[3] == 1) { return 56; }
			else if (threes[5] == 1 && pairs[2] == 1) { return 55; }
			else if (threes[5] == 1 && pairs[1] == 1) { return 54; }
			else if (threes[5] == 1 && pairs[0] == 1) { return 53; }
			else if (threes[4] == 1 && pairs[5] == 1) { return 52; }
			else if (threes[3] == 1 && pairs[5] == 1) { return 51; }
			else if (threes[2] == 1 && pairs[5] == 1) { return 50; }
			else if (threes[1] == 1 && pairs[5] == 1) { return 49; }
			else if (threes[0] == 1 && pairs[5] == 1) { return 48; }
			// mario cases
			else if (threes[4] == 1 && pairs[3] == 1) { return 47; }
			else if (threes[4] == 1 && pairs[2] == 1) { return 46; }
			else if (threes[4] == 1 && pairs[1] == 1) { return 45; }
			else if (threes[4] == 1 && pairs[0] == 1) { return 44; }
			else if (threes[3] == 1 && pairs[4] == 1) { return 43; }
			else if (threes[2] == 1 && pairs[4] == 1) { return 42; }
			else if (threes[1] == 1 && pairs[4] == 1) { return 41; }
			else if (threes[0] == 1 && pairs[4] == 1) { return 40; }
			// luigi cases
			else if (threes[3] == 1 && pairs[2] == 1) { return 39; }
			else if (threes[3] == 1 && pairs[1] == 1) { return 38; }
			else if (threes[3] == 1 && pairs[0] == 1) { return 37; }
			else if (threes[2] == 1 && pairs[3] == 1) { return 36; }
			else if (threes[1] == 1 && pairs[3] == 1) { return 35; }
			else if (threes[0] == 1 && pairs[3] == 1) { return 34; }
			// flower case
			else if (threes[2] == 1 && pairs[1] == 1) { return 33; }
			else if (threes[2] == 1 && pairs[0] == 1) { return 32; }
			else if (threes[1] == 1 && pairs[2] == 1) { return 31; }
			else if (threes[0] == 1 && pairs[2] == 1) { return 30; }
			// mushroom/cloud cases
			else if (threes[1] == 1 && pairs[0] == 1) { return 29; }
			else if (threes[0] == 1 && pairs[1] == 1) { return 28; }
		}
		
		//Triples
		if (hasThree) {
			if (threes[5] == 1)      { return 27; } 
			else if (threes[4] == 1) { return 26; } 
			else if (threes[3] == 1) { return 25; } 
			else if (threes[2] == 1) { return 24; } 
			else if (threes[1] == 1) { return 23; } 
			else if (threes[0] == 1) { return 22; }
		}
		
		if (numPairs == 2) {
			if (pairs[5] == 1 && pairs[4] == 1) { return 21; }
			else if (pairs[5] == 1 && pairs[3] == 1) { return 20; }
			else if (pairs[5] == 1 && pairs[2] == 1) { return 19; }
			else if (pairs[5] == 1 && pairs[1] == 1) { return 18; }
			else if (pairs[5] == 1 && pairs[0] == 1) { return 17; }
			else if (pairs[4] == 1 && pairs[3] == 1) { return 16; }
			else if (pairs[4] == 1 && pairs[2] == 1) { return 15; }
			else if (pairs[4] == 1 && pairs[1] == 1) { return 14; }
			else if (pairs[4] == 1 && pairs[0] == 1) { return 13; }
			else if (pairs[3] == 1 && pairs[2] == 1) { return 12; }
			else if (pairs[3] == 1 && pairs[1] == 1) { return 11; }
			else if (pairs[3] == 1 && pairs[0] == 1) { return 10;  }
			else if (pairs[2] == 1 && pairs[1] == 1) { return 9;  }
			else if (pairs[2] == 1 && pairs[0] == 1) { return 8;  }
			else if (pairs[1] == 1 && pairs[0] == 1) { return 7;  }
		} 
		
		if (numPairs == 1) {
			if (pairs[5] == 1) { return 6; }
			else if (pairs[4] == 1) { return 5; }
			else if (pairs[3] == 1) { return 4; }
			else if (pairs[2] == 1) { return 3; }
			else if (pairs[1] == 1) { return 2; }
			else if (pairs[0] == 1) { return 1; }
		}
		
		if (numSingles == 5) {
			return 0;
		}
		
		
		
		return -1;
	}
}
