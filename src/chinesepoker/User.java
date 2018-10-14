package chinesepoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User extends Hand implements Player{
	public static String name;

	public User(ArrayList<Card> h) {
		super(h);
	}
	
	public ArrayList<Card> play(ArrayList<Card> x){
		Scanner s = new Scanner(System.in);
		System.out.println(name + "'s hand: " + x);
		System.out.println("Type in the cards you'd like to play, adding a space after each. Enter a period when completed. Enter pass to pass.");
		ArrayList<Card> cardsEntered = new ArrayList<Card>();
		int[] positions = {13, 13, 13, 13, 13};
		int counter = 0;
		while(s.hasNext()) {
			String entered = s.next(); 
			if (entered.equals("pass")) {
				if (hasThreeOfDiamonds(x)) {
					System.out.println("You cannot pass on the first turn.");
					play(x);
				}
				else {
					counter = 0;
					break;
				}
			}
			else {
				if (entered.equals(".")) {
					Arrays.sort(positions);
					break;
				}
				else if (entered.length()<2) { //invalid card is entered
					counter=6;
					break;
				}
				else {
					Card c = Card.findCard(entered);
					cardsEntered.add(c);
					for (int i = 0; i < x.size(); i++) {
						if (x.get(i).getRank().getRankValue() == c.getRank().getRankValue()
							&& x.get(i).getSuit().getSuitValue() == c.getSuit().getSuitValue()) {
							positions[counter] = i;
						}
						}
						counter++; //tracks how many cards are entered
					}
				}
			} 
			
			if (counter == 0) 
			{
				pass();
				if (isEmpty(x)){
					Game.win(Game.playerList.get(Game.currPlayer));
					s.close();
				}
				return new ArrayList<Card>();
				
			}
			else if (counter == 1 && User.isValid(x, cardsEntered.get(0)) && (canPlayOn(Game.lastPlayed, cardsEntered.get(0))) )
			{
				x.remove(positions[0]);
				passCounter=0;
				Game.setLastPlayed(cardsEntered);
				if (isEmpty(x)){
					Game.win(Game.playerList.get(Game.currPlayer));
					s.close();
				}
				return cardsEntered;
			}
			else if (counter == 2 && User.isValid(x, cardsEntered.get(0), cardsEntered.get(1))
					&& canPlayOn(Game.lastPlayed, cardsEntered.get(0), cardsEntered.get(1))) 
			{
				x.remove(positions[1]); x.remove(positions[0]);
				Game.setLastPlayed(cardsEntered);
				passCounter=0;
				if (isEmpty(x)){
					Game.win(Game.playerList.get(Game.currPlayer));
					s.close();
				}
				return cardsEntered;
			}	
			else if (counter == 5 
					&& User.isValid(x, cardsEntered.get(0), cardsEntered.get(1), cardsEntered.get(2), cardsEntered.get(3), cardsEntered.get(4)) 
					&& canPlayOn(Game.lastPlayed, cardsEntered.get(0), cardsEntered.get(1), cardsEntered.get(2), cardsEntered.get(3), cardsEntered.get(4))) 
			{
				x.remove(positions[4]); x.remove(positions[3]); x.remove(positions[2]);
				x.remove(positions[1]); x.remove(positions[0]);
				passCounter=0;
				Game.setLastPlayed(cardsEntered);
				if (isEmpty(x)){
					Game.win(Game.playerList.get(Game.currPlayer));
					s.close();
				}
				return cardsEntered;
			}
			else {
				if (isEmpty(x)){
					Game.win(Game.playerList.get(Game.currPlayer));
					s.close();
				}
			}
			System.out.println("An invalid play was entered. Enter a valid combination or pass.");
			return play(x);
			
	}
	/*
	 * Checking if cards entered are valid plays.
	 */
	public static boolean isValid(ArrayList<Card> h, Card a){	
		if (hasThreeOfDiamonds(h)) {
			Card x = new Card(Card.Rank.values()[0], Card.Suit.values()[0]);
			if (a.getRank().getRankValue() == x.getRank().getRankValue() && a.getSuit().getSuitValue() == x.getSuit().getSuitValue()) {
				return true;
			}
			else {
				System.out.println("Must play Three of Diamonds on first turn.");
				return false;
			}
		}
		if (h.size()==1) {
			if (a.getRank().getRankValue()==15 && a.getSuit().getSuitValue()==4) {
				System.out.println("Your last card cannot be the two of spades. You have lost.");
				System.exit(-1);
			}
		}
		for (int i = 0; i < h.size(); i++){
			if (h.get(i).getSuit().getSuitValue() == a.getSuit().getSuitValue()
					&& h.get(i).getRank().getRankValue() == a.getRank().getRankValue()){
				return true;
			}		 
		}

		return false;
	}
	
	public static boolean isValid(ArrayList<Card> h, Card a, Card b) {
		if (hasThreeOfDiamonds(h)) {
			Card x = new Card(Card.Rank.values()[0], Card.Suit.values()[0]);
			if (a.getRank().getRankValue() == x.getRank().getRankValue() && a.getSuit().getSuitValue() == x.getSuit().getSuitValue()
					|| b.getRank().getRankValue() == x.getRank().getRankValue() && b.getSuit().getSuitValue() == x.getSuit().getSuitValue()) {
				return true;
			}
			else {
				System.out.println("Must play Three of Diamonds on first turn.");
				return false;	
			}
		}
		if (a.getSuit().getSuitValue()==b.getSuit().getSuitValue()) {
			return false;
		}
		ArrayList<Card> copy = new ArrayList<Card>(2);
		copy.add(a);copy.add(b);
		sortHand(copy);
		int counter=0;
		for (int i = 0; i < h.size(); i++) {
			for (int j = 0; j < copy.size(); j++) {
				if (h.get(i).getRank().getRankValue() == copy.get(j).getRank().getRankValue()) {
					if (h.get(i).getSuit().getSuitValue() == copy.get(j).getSuit().getSuitValue()) {
						counter++;
					}
				}
			}
		}
		if (counter==2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isValid(ArrayList<Card> h, Card a, Card b, Card c, Card d, Card e) {
		ArrayList<Card> cardCheck = new ArrayList<Card>(5);
		cardCheck.add(a);cardCheck.add(b);cardCheck.add(c);cardCheck.add(d);cardCheck.add(e);	
		sortHand(cardCheck);
		if (hasThreeOfDiamonds(h)) {
			Card x = new Card(Card.Rank.values()[0], Card.Suit.values()[0]);
			if (cardCheck.get(0).getSuit().getSuitValue() == x.getSuit().getSuitValue()
					|| cardCheck.get(0).getRank().getRankValue() == x.getRank().getRankValue()) {
				return true;
			}
			else {
				System.out.println("Must play Three of Diamonds on first turn.");
				return false;	
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = i+1; j < 5; j++) {
				if (cardCheck.get(i).getRank().getRankValue()==cardCheck.get(j).getRank().getRankValue()) {
					if (cardCheck.get(i).getSuit().getSuitValue()==cardCheck.get(j).getSuit().getSuitValue()) {
						return false;
					}
				}	
			}
		}
		
		int counter = 0;
		for (int i = 0; i < h.size(); i++) {
			for (int j = 0; j < cardCheck.size(); j++) {
				if (h.get(i).getRank().getRankValue() == cardCheck.get(j).getRank().getRankValue()) {
					if (h.get(i).getSuit().getSuitValue() == cardCheck.get(j).getSuit().getSuitValue()) {
						counter++;
					}
				}
			}
		}
		if (counter==5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Card> getIndex(int currPlayer){
		return null;
	}
	
	public static void setName(String s) {
		name = s;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString() {
		return name.toString();
	}
}