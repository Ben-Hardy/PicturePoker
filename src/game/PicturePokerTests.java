package game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PicturePokerTests {

	@Test
	void test() {
		Card card = new Card("Test", 2);
		assertEquals(card.getPicture(), "Test");
		assertEquals(card.getId(), 2);
		
		Deck deck = new Deck();
		Card drawnCard = deck.draw();
		assertNotNull(drawnCard);
		assertEquals(deck.getDeck().size(), 6);
		
		Hand hand = new Hand();
		
		for (int i = 0; i < 5; i++) {
			assertNotNull(hand.cardAt(i));
		}
		
		ArrayList<Integer> cardsToSwap = new ArrayList<Integer>();
		cardsToSwap.add(0);
		cardsToSwap.add(2);
		cardsToSwap.add(3);
		hand.swapCards(cardsToSwap);
		
		for (int i = 0; i < 5; i++) {
			assertNotNull(hand.cardAt(i));
		}
		
	}
	
	@Test
	void analyzeHandTests() {
		// now to test analyzeHand
		// first we need to be able to set up each possible hand. to do so we
		// will have to manually create the various hands
		
		Deck deck = new Deck();
		
		Card star = deck.deck.get(5);
		Card mario = deck.deck.get(4);
		Card luigi = deck.deck.get(3);
		Card flower = deck.deck.get(2);
		Card mushroom = deck.deck.get(1);
		Card cloud = deck.deck.get(0);
		
		Hand testHand = new Hand();
		
		// test for junk hand
		testHand.swapAt(cloud, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(luigi, 2);
		testHand.swapAt(mario, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(0, testHand.analyzeHand());
		
		/* now for the pairs
		 *  1 pair:
		 *  	cloud    - 1
		 *  	mushroom - 2
		 *      flower   - 3
		 *      luigi    - 4
		 *      mario    - 5
		 *      star     - 6
		 */
		testHand.swapAt(cloud, 1);
		assertEquals(1, testHand.analyzeHand());
		testHand.swapAt(mushroom, 1);
		assertEquals(2, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		assertEquals(3, testHand.analyzeHand());
		testHand.swapAt(luigi, 1);
		assertEquals(4, testHand.analyzeHand());
		testHand.swapAt(mario, 1);
		assertEquals(5, testHand.analyzeHand());
		testHand.swapAt(star, 0);
		testHand.swapAt(star, 1);
		assertEquals(6, testHand.analyzeHand());
		
		/* two pair tests
		 *  2 pairs: 
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
		 */
		testHand.swapAt(star, 4);
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(mushroom, 3);
		assertEquals(7, testHand.analyzeHand());
		testHand.swapAt(flower, 2);
		testHand.swapAt(flower, 3);
		assertEquals(8, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		assertEquals(9, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(cloud, 3);
		assertEquals(10, testHand.analyzeHand());
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(mushroom, 3);
		assertEquals(11, testHand.analyzeHand());
		testHand.swapAt(flower, 2);
		testHand.swapAt(flower, 3);
		assertEquals(12, testHand.analyzeHand());
		testHand.swapAt(mario, 0);
		testHand.swapAt(mario, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(cloud, 3);
		assertEquals(13, testHand.analyzeHand());
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(mushroom, 3);
		assertEquals(14, testHand.analyzeHand());
		testHand.swapAt(flower, 2);
		testHand.swapAt(flower, 3);
		assertEquals(15, testHand.analyzeHand());
		testHand.swapAt(luigi, 2);
		testHand.swapAt(luigi, 3);
		assertEquals(16, testHand.analyzeHand());
		testHand.swapAt(mario, 4);
		testHand.swapAt(star, 0);
		testHand.swapAt(star, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(cloud, 3);
		assertEquals(17, testHand.analyzeHand());
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(mushroom, 3);
		assertEquals(18, testHand.analyzeHand());
		testHand.swapAt(flower, 2);
		testHand.swapAt(flower, 3);
		assertEquals(19, testHand.analyzeHand());
		testHand.swapAt(luigi, 2);
		testHand.swapAt(luigi, 3);
		assertEquals(20, testHand.analyzeHand());
		testHand.swapAt(mario, 2);
		testHand.swapAt(mario, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(21, testHand.analyzeHand());
		
		/* Triples tests
		 * 		 *  3 of a kind:
		 *  	cloud             - 22
		 *      mushroom          - 23
		 *      flower            - 24
		 *      luigi             - 25
		 *      mario             - 26
		 *      star              - 27
		 */
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(star, 3);
		testHand.swapAt(mario, 4);
		assertEquals(22, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		assertEquals(23, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		assertEquals(24, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(luigi, 2);
		assertEquals(25, testHand.analyzeHand());
		testHand.swapAt(cloud, 3);
		testHand.swapAt(mushroom, 4);
		testHand.swapAt(mario, 0);
		testHand.swapAt(mario, 1);
		testHand.swapAt(mario, 2);
		assertEquals(26, testHand.analyzeHand());
		testHand.swapAt(star, 0);
		testHand.swapAt(star, 1);
		testHand.swapAt(star, 2);
		assertEquals(27, testHand.analyzeHand());
		
		/* full house tests 
		 * 		cloud/mushroom    - 28
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
		 */
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(mushroom, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(28, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(29, testHand.analyzeHand());
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(flower, 3);
		testHand.swapAt(flower, 4);
		assertEquals(30, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		assertEquals(31, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(32, testHand.analyzeHand());
		testHand.swapAt(mushroom, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(33, testHand.analyzeHand());
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(luigi, 3);
		testHand.swapAt(luigi, 4);
		assertEquals(34, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		assertEquals(35, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		assertEquals(36, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(luigi, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(37, testHand.analyzeHand());
		testHand.swapAt(mushroom, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(38, testHand.analyzeHand());
		testHand.swapAt(flower, 3);
		testHand.swapAt(flower, 4);
		assertEquals(39, testHand.analyzeHand());
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(mario, 3);
		testHand.swapAt(mario, 4);
		assertEquals(40, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		assertEquals(41, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		assertEquals(42, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(luigi, 2);
		assertEquals(43, testHand.analyzeHand());
		testHand.swapAt(mario, 0);
		testHand.swapAt(mario, 1);
		testHand.swapAt(mario, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(44, testHand.analyzeHand());
		testHand.swapAt(mushroom, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(45, testHand.analyzeHand());
		testHand.swapAt(flower, 3);
		testHand.swapAt(flower, 4);
		assertEquals(46, testHand.analyzeHand());
		testHand.swapAt(luigi, 3);
		testHand.swapAt(luigi, 4);
		assertEquals(47, testHand.analyzeHand());
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(star, 3);
		testHand.swapAt(star, 4);
		assertEquals(48, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		assertEquals(49, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		assertEquals(50, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(luigi, 2);
		assertEquals(51, testHand.analyzeHand());
		testHand.swapAt(mario, 0);
		testHand.swapAt(mario, 1);
		testHand.swapAt(mario, 2);
		assertEquals(52, testHand.analyzeHand());
		testHand.swapAt(star, 0);
		testHand.swapAt(star, 1);
		testHand.swapAt(star, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(53, testHand.analyzeHand());
		testHand.swapAt(mushroom, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(54, testHand.analyzeHand());
		testHand.swapAt(flower, 3);
		testHand.swapAt(flower, 4);
		assertEquals(55, testHand.analyzeHand());
		testHand.swapAt(luigi, 3);
		testHand.swapAt(luigi, 4);
		assertEquals(56, testHand.analyzeHand());
		testHand.swapAt(mario, 3);
		testHand.swapAt(mario, 4);
		assertEquals(57, testHand.analyzeHand());
		
		/* four of a kind
		 *		cloud             - 58
		 *  	mushroom          - 59
		 *  	flower            - 60
		 *  	luigi             - 61
		 *  	mario             - 62
		 *  	star              - 63
		 * 
		 */
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(star, 4);
		assertEquals(58, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(mushroom, 3);
		assertEquals(59, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		testHand.swapAt(flower, 3);
		assertEquals(60, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(luigi, 2);
		testHand.swapAt(luigi, 3);
		assertEquals(61, testHand.analyzeHand());
		testHand.swapAt(mario, 0);
		testHand.swapAt(mario, 1);
		testHand.swapAt(mario, 2);
		testHand.swapAt(mario, 3);
		assertEquals(62, testHand.analyzeHand());
		testHand.swapAt(cloud, 4);
		testHand.swapAt(star, 0);
		testHand.swapAt(star, 1);
		testHand.swapAt(star, 2);
		testHand.swapAt(star, 3);
		assertEquals(63, testHand.analyzeHand());
		
		/* five of a kind
		 *  	cloud             - 64
		 *  	mushroom          - 65
		 *  	flower            - 66
		 *  	luigi             - 67
		 *  	mario             - 68
		 *  	star              - 69
		 */
		testHand.swapAt(cloud, 0);
		testHand.swapAt(cloud, 1);
		testHand.swapAt(cloud, 2);
		testHand.swapAt(cloud, 3);
		testHand.swapAt(cloud, 4);
		assertEquals(64, testHand.analyzeHand());
		testHand.swapAt(mushroom, 0);
		testHand.swapAt(mushroom, 1);
		testHand.swapAt(mushroom, 2);
		testHand.swapAt(mushroom, 3);
		testHand.swapAt(mushroom, 4);
		assertEquals(65, testHand.analyzeHand());
		testHand.swapAt(flower, 0);
		testHand.swapAt(flower, 1);
		testHand.swapAt(flower, 2);
		testHand.swapAt(flower, 3);
		testHand.swapAt(flower, 4);
		assertEquals(66, testHand.analyzeHand());
		testHand.swapAt(luigi, 0);
		testHand.swapAt(luigi, 1);
		testHand.swapAt(luigi, 2);
		testHand.swapAt(luigi, 3);
		testHand.swapAt(luigi, 4);
		assertEquals(67, testHand.analyzeHand());
		testHand.swapAt(mario, 0);
		testHand.swapAt(mario, 1);
		testHand.swapAt(mario, 2);
		testHand.swapAt(mario, 3);
		testHand.swapAt(mario, 4);
		assertEquals(68, testHand.analyzeHand());
		testHand.swapAt(star, 0);
		testHand.swapAt(star, 1);
		testHand.swapAt(star, 2);
		testHand.swapAt(star, 3);
		testHand.swapAt(star, 4);
		assertEquals(69, testHand.analyzeHand());
		
	}
	
	@Test
	void picturePokerClassTests() {
		PicturePoker pp = new PicturePoker();
		
		// since we need to test that PicturePoker's assessWinner function, we will manually set hands to do so
		Deck deck = new Deck();
		
		Card star = deck.deck.get(5);
		Card mario = deck.deck.get(4);
		Card luigi = deck.deck.get(3);
		Card flower = deck.deck.get(2);
		Card mushroom = deck.deck.get(1);
		Card cloud = deck.deck.get(0);
		
		// the best hand
		Hand hand1 = new Hand();
		hand1.swapAt(star, 0);
		hand1.swapAt(star, 1);
		hand1.swapAt(star, 2);
		hand1.swapAt(star, 3);
		hand1.swapAt(star, 4);
		
		//  the worst hand
		Hand hand2 = new Hand();
		hand2.swapAt(mario, 0);
		hand2.swapAt(mushroom, 1);
		hand2.swapAt(cloud, 2);
		hand2.swapAt(flower, 3);
		hand2.swapAt(luigi, 4);
		
		pp.setHands(hand2, hand1);
		
		// first, test that assessWinner returns 1 when player wins
		assertEquals(1, pp.assessWinner());
		
		// next, test assessWinner returns -1 if the dealer hand is better
		pp.setHands(hand1, hand2);
		assertEquals(-1, pp.assessWinner());
		
		// finally, make sure ties are handled
		pp.setHands(hand1, hand1);
		assertEquals(0, pp.assessWinner());
		
		pp.play();
		
	}
	

}
