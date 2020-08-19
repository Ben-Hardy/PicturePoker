package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class for a deck of Cards. For Picture Poker, this means a deck
 * containing one of each of the six possible cards, and we will be 
 * randomly choosing cards from this deck with equal probability.
 * @author benjaminhardy
 */
public class Deck {
	
	/**
	 * An ArrayList of Cards that represents our deck of cards
	 */
	ArrayList<Card> deck;
	
	/**
	 * Constructor for the Deck class. Creates a new Deck containing
	 * a Card with each of the six pictures
	 */
	public Deck() {
		deck = new ArrayList<Card>();
		deck.add(new Card("Cloud", 0));
		deck.add(new Card("Mushroom", 1));
		deck.add(new Card("Flower", 2));
		deck.add(new Card("Luigi", 3));
		deck.add(new Card("Mario", 4));
		deck.add(new Card("Star", 5));
	}
	
	/**
	 * function for testing that allows us direct access to the deck of cards
	 * @return deck, the Deck of cards this class contains
	 */
	protected ArrayList<Card> getDeck() {
		return deck;
	}
	
	/**
	 * Draws a card from the deck by generating a random number from 0 to 5  and returning
	 * the Card at that index
	 * @return the card at the index of selection, the randomly generated number
	 */
	public Card draw() {
		Random random = new Random();
		int selection = random.nextInt(6);
		return deck.get(selection);
	}
}
