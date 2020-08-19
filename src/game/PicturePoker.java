package game;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * A command line attempt at Picture Poker found in the various Mario games for the Nintendo DS
 * 
 * This game works similar to regular poker there's only 6 possible cards and you have hands of 5 cards
 * You either hold or swap out between 1 and 5 cards, then your hand is assessed against the computer's
 * 
 * You also are able to gamble between 1 and 5 coins which you can earn back by winning or tying. If you
 * win, the type of hand you win with will determine how much money you earn. 
 * one pair        - 2x whatever you bet (maximum 10 coins, so you earn 5)
 * two pairs       - 3x whatever you bet (maximum 15 coins, so you earn 10)
 * three of a kind - 4x whatever you bet (maximum 20 coins, so you earn 15)
 * full house      - 6x whatever you bet (maximum 30 coins, so you earn 25)
 * four of a kind  - 8x whatever you bet (maximum 40 coins, so you earn 35)
 * five of a kind - 16x whatever you bet (maximum 80 coins, so you earn 75)
 * 
 * @author benjaminhardy
 *
 */
public class PicturePoker {
	private Hand dealerHand;
	private Hand playerHand;
	
	private int coins;
	
	private Deck dealerDeck;
	
	public PicturePoker() {
		coins = 5;
	}
	
	/**
	 * function that lets you set the dealer and player hands for testing purposes. 
	 * @param dealerHand the Hand you wish to set as the dealer's hand
	 * @param playerHand the Hand you wish to set as the player's hand
	 */
	protected void setHands(Hand dealerHand, Hand playerHand) {
		this.dealerHand = dealerHand;
		this.playerHand = playerHand;
	}
	
	/**
	 * checks who won the round of PicturePoker
	 * @return 1 if the player won, -1 if dealer won, 0 if tie
	 */
	public int assessWinner() {
		
		int dealerScore = dealerHand.analyzeHand();
		int playerScore = playerHand.analyzeHand();
		
		if (playerScore > dealerScore) {
			return 1;
		} else if (dealerScore == playerScore) {
			return 0;
		} else return -1;
	}
	
	/**
	 * swaps out Cards at each index in toSwap by passing it on  to the hand's swapCards function
	 * @param toSwap - an ArrayList of integers representing the indices
	 * of hand array that need to be swapped out. 
	 */
	public void swap(ArrayList<Integer> toSwap) {
		playerHand.swapCards(toSwap);
	}
	
	public void play() {
		Boolean quit = false;
		Boolean isNewHand = true;
		Boolean badInput = false;
		Scanner in = new Scanner(System.in);
		int ante = 1;
		
		System.out.println("Welcome to Picture Poker!");
		printHelp();
		
		
		while (!quit) {
			
			if (isNewHand) {
				dealerHand = new Hand();
				playerHand = new Hand();				
				isNewHand = false;
				ante = 1;
			}
			
			badInput = false;
			
			System.out.println("\nCurrent hand: " + playerHand.toString());
			System.out.println("Current coins: " + coins);
			System.out.println("Current bet: " + ante);
			System.out.print("Input your command: ");
			String[] input = in.nextLine().split(" ");
			
			if (input[0].contentEquals("quit")) {
				quit = true;
			}  else if (input[0].contentEquals("hold")) {
				printWinner();
				processBet(ante);
				isNewHand = true;
			} else if (input[0].contentEquals("draw")) {
				ArrayList<Integer> toSwap = new ArrayList<Integer>();
				
				for (int i = 1; i < input.length; i++) {
					System.out.println(input[i]);
					try {
						int idx = Integer.parseInt(input[i]);
						
						if (idx < 1 || idx > 5) {
							System.out.println("the input numbers for the draw command must be between 1 and 5 inclusive");
							badInput = true;
							break;
						} else {
							toSwap.add(idx - 1);
						}
					} catch (Exception e) {
						System.out.println("One or more numbers for your draw command were not of the right format");
						badInput = true;
					}
				}
				
				if (!badInput) {
					playerHand.swapCards(toSwap);
					
					printWinner();
					processBet(ante);
					isNewHand = true;
				}
			}
				else if (input[0].contentEquals("ante")) {
					int bet = 0;
					try {
						bet = Integer.parseInt(input[1]);
						
						if ((bet < 1 || bet > 5) || (bet + ante < 1 || bet + ante > 5)) {
							badInput = true;
							System.out.println("Bet must be between 1 and 4, and total bet must not surpass 5.");
						}
						
					} catch (Exception e) {
						System.out.println("Your ante command was not formatted correctly");
						badInput = true;
					}
					
					if (!badInput) {
						if (bet + ante > coins) {
							System.out.println("You can't bet more coins than you have available!");
						} else {
							ante += bet;	
						}
						
					}	
				} else if (input[0].contentEquals("")) {
				System.out.println("Oops! Invalid command!");
				printHelp();
			} else {

			}
		}
		
		in.close();
	}
	
	private void printWinner() {
		int winner = assessWinner();
		
		System.out.println("Player Hand: " + playerHand.toString());
		System.out.println("Dealer Hand: " + dealerHand.toString());
		
		if (winner == 1) {
			System.out.println("You win!");
		} else if (winner == -1) {
			System.out.println("Dealer won!");
		} else {
			System.out.println("Tie!");
		}
	}
	
	private void processBet(int ante) {
		int winner = assessWinner();
		
		if (winner == 1) {
			int handCode = playerHand.analyzeHand();
			
			if (1 <= handCode && handCode <= 6)
				coins += 2 * ante;
			else if (7 <= handCode && handCode <= 21)
				coins += 3 * ante;
			else if (22 <= handCode && handCode <= 27)
				coins += 4 * ante;
			else if (28 <= handCode && handCode <= 57)
				coins += 6 * ante;
			else if (58 <= handCode && handCode <= 63)
				coins += 8 * ante;
			else if (64 <= handCode && handCode <= 69)
				coins += 16 * ante;
		} else if (winner == -1) {
			coins -= ante;
		}
	}
	
	public void printHelp() {
		System.out.println("Picture Poker Rules:");
		System.out.println("\nThere are 4 possible commands:");
		System.out.println("    draw [1 2 3 4 5]  <- Draws new cards at up to 5 positions");
		System.out.println("    hold  <- Hold with your current hand. Ends round");
		System.out.println("    ante x   <- Ups your current bet by x. The  maximum bet is 5 so x must be less than or equal to 5 - current bet");
		System.out.println("    quit  <- quits the game");
	}
}
