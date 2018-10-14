package chinesepoker;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	static Deck deck = new Deck();
	static int currPlayer;	
	static User player1; static ArrayList<Card> p1 = new ArrayList<Card>();
	static Dumb_Player player2;  static ArrayList<Card> p2 = new ArrayList<Card>();
	private static Dumb_Player2 player3; static ArrayList<Card> p3 = new ArrayList<Card>();
	static Smart_Player player4; static ArrayList<Card> p4 = new ArrayList<Card>();
	static ArrayList<Player> playerList = new ArrayList<Player>();
	static ArrayList<Integer> hands = new ArrayList<Integer>();
	static int playersLeft = 4;
	static ArrayList<Card> lastPlayed = new ArrayList<Card>();
	
	public Game(Deck d) {
		deck = d;
	}
	
	public static void setGame() {
		deck.setHands(p1);
		Hand.sortHand(p1);
		player1 = new User(p1);
		
		deck.setHands(p2);
		Hand.sortHand(p2);
		player2 = new Dumb_Player(p2);
		
		deck.setHands(p3);
		Hand.sortHand(p3);
		player3 = new Dumb_Player2(p3);
		
		deck.setHands(p4);
		Hand.sortHand(p4);
		player4 = new Smart_Player(p4);
		
		if (Hand.hasThreeOfDiamonds(p1)) {
			currPlayer = 0;
		}
		if (Hand.hasThreeOfDiamonds(p2)) {
			currPlayer = 1;
		}
		if (Hand.hasThreeOfDiamonds(p3)) {
			currPlayer = 2;
		}
		if (Hand.hasThreeOfDiamonds(p4)) {
			currPlayer = 3;
		}
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		playerList.add(player4);
		setIndexArrayList();
	}
	
	public static void setIndexArrayList() {
		hands.add(1);hands.add(2);hands.add(3);hands.add(4);
	}
	
	public static int getIndex(){
		return hands.get(currPlayer);
	}
	
	public static ArrayList<Card> getPlayerHand(int x){
		if (x == 1) {
			return p1;
		}
		else if (x == 2) {
			return p2;
		}
		else if (x == 3) {
			return p3;
		}
		else{
			return p4;
		}
	}
	
	public static void setLastPlayed(ArrayList<Card> lastPlay) {
		lastPlayed = lastPlay;
	}
	
	public static ArrayList<Card> getLastPlayed() {
		return lastPlayed;
	}

	public static void removeFromHand() {
		hands.remove(currPlayer);
	}
	
	public static int getNextPlayer() {
		if (playersLeft == 4) 
		{
			if (currPlayer == 3) {
				currPlayer=0;
			}
			else{
				currPlayer++;
			}
		}
		else if (playersLeft == 3){
			if (currPlayer==2){ 
				currPlayer =0;
			}
			else {
				currPlayer++;
			}
		}
		else {
			if (currPlayer ==1) {
				currPlayer=0;
			}
			else {
				currPlayer++;
			}
		}
		return currPlayer;
	}
	
	public static void removePlayer() {
		playerList.remove(currPlayer);
		removeFromHand();
		playersLeft--;
		currPlayer = currPlayer -1;
		}
	
	public static void win(Player p) {
		Hand.passCounter =-1;
		if (hands.size()==4) {
			System.out.println(playerList.get(currPlayer) + " wins!");
			removePlayer();
		}
		else if (hands.size()==3) {
			System.out.println(playerList.get(currPlayer) + " comes in second place!");
			removePlayer();
		}
		else {
			System.out.println(playerList.get(currPlayer) + " finishes in third place!");
			getNextPlayer();
			System.out.println(playerList.get(currPlayer) + " finishes in last place.");
			System.exit(0);
		}
	}
/*
 * Start game
 */
	public static void main(String[] args) {
		System.out.println("Let's Play Chinese Poker!");
		
		//RULES FOR PLAYING CARDS
		System.out.println("");
		System.out.println("RULES:");
		System.out.println("The cards are divided evenly among four players.\n" + 
				"The three of diamonds must be played first, with any valid combination of cards.\n" + 
				"Players take turns placing a valid card combination greater than the last.\n" + 
				"If a player cannot or does not want to play any cards their turn, they may pass.\n" + 
				"If all players pass on their turns, then the player who placed the last played cards can play any new card combination.\n" + 
				"Play continues until only one player remains.\n" + 
				"Valid card plays:\n" + 
				"	Rank order: 3 has the lowest value, 2 has the greatest value (3<4<5<6<7<8<9<10<J<Q<K<A<2)\n" + 
				"	Suit order: diamonds < clubs < hearts < spades\n" + 
				"	Card Combinations (in order from lowest to highest):\n" +
				"		Single card\n" + 
				"		Pair of cards\n" + 
				"		Straight (5 cards in order)\n" + 
				"		Flush (5 cards of the same suit)\n" + 
				"		Full house (3 of the same number cards + pair)\n" + 
				"		Bomb (4 of the same number cards + single)\n" + 
				"		Straight flush (5 cards in order and in the same suit)\n" + 
				"		Royal flush (straight flush but from 10 to A)\n" + 
				"	A straight containing both a 2 and a 3 is an invalid combination.\n" + 
				"	A player's last play cannot be a single 2 of spades.\n" + 
				"	When playing a pair of cards, the value of the higher card determines its rank.\n");
		
	
		//RULES FOR ENTERING CARDS
		System.out.println("To enter cards, type the value of the card followed by the first letter of its suit and add a space.");
		System.out.println("Cards 2 through 9 are selected by their respective digit.");
		System.out.println("Cards TEN through ACE are selected by their respective lowercase initial.");
		System.out.println("For example, TWO OF DIAMONDS should be entered as 2d ");
		System.out.println("JACK OF SPADES should be entered as js ");
		System.out.println("TEN OF HEARTS should be entered as th ");
		System.out.println("Have fun!");
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		Game.setGame();
		System.out.println("Enter your name: ");
		String name = scan.nextLine();
		User.setName(name);
		System.out.println(playerList.get(currPlayer) + " has the three of diamonds and begins.");
		while (playersLeft > 1) {
			System.out.println();
			System.out.println(playerList.get(currPlayer) + "'s turn.");
			if (Hand.passCounter == playersLeft -1) {
				System.out.println(playerList.get(currPlayer) + " has a free play.");
				lastPlayed.clear();
			}
			System.out.print(playerList.get(currPlayer) + " has ");
			if (playerList.get(currPlayer)==player4) {
				int x = getPlayerHand(getIndex()).size() + Hand.singles.size() + Hand.doubles.size() + Hand.straight.size() + Hand.flush.size() 
				+ Hand.fullHouse.size() + Hand.bomb.size() + Hand.straightFlush.size() + Hand.royalFlush.size();					
				System.out.println(x + " card(s).");
				}
			else {
				System.out.println(getPlayerHand(getIndex()).size() + " card(s).");
			}
			System.out.println("Last played: " + lastPlayed);
			System.out.println(playerList.get(currPlayer) + " plays: " + playerList.get(currPlayer).play(getPlayerHand(getIndex())));
			getNextPlayer();
		}
		scan.close();
		}
}
		