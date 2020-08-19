package game;

/**
 * A class for an individual Card, which in Picture Poker, simply is represented
 * by a picture
 * @author benjaminhardy
 *
 */
public class Card {
	/**
	 * id - an integer ID for each card, for convenient comparison
	 */
	private int id;
	
	/**
	 * picture - a string representation of the given picture
	 */
	private String picture;
	
	/**
	 * Constructor function. Creates new Card with given inputs
	 * @param picture - the String representation of the picture on the card
	 * @param id - the integer ID of the card
	 */
	public Card(String picture, int id) {
		this.id = id;
		this.picture = picture;
	}
	
	/**
	 * @return id, the Card's ID
	 */
	public int getId() { return this.id; }
	
	/**
	 * @return String representation of the Card's picture
	 */
	public String getPicture() { return picture; }
	
	/**
	 * @return string representation of the card
	 */
	public String toString() {
		return "Picture: " + this.picture + ", id: " + this.id;
	}
}
