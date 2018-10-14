package chinesepoker;

import java.util.ArrayList;
import java.util.Collections;

import chinesepoker.Card.Rank;
import chinesepoker.Card.Suit;

public class Deck {
	ArrayList<Card> deck;
	private int counter = 0;
	
	/*
	 * Creates a deck of cards
	 */
	public Deck()
	{
		deck = new ArrayList<Card>();
		for (Suit suit : Suit.values())
		{
			for (Rank rank : Rank.values())
			{
				deck.add(new Card(rank, suit));
			}
		}
		Collections.shuffle(deck);
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	/*
	 * distributes 13 cards to each player
	 */
	public void setHands(ArrayList<Card> p1) {
		if (counter==0) {
			for (int i = 0; i <13; i++) {
				p1.add(deck.get(i));
			}
			counter++;
		}
		else if (counter==1) {
			for (int i = 13; i <26; i++) {
				p1.add(deck.get(i));
			}
			counter++;
		}
		else if (counter==2) {
			for (int i = 26; i <39; i++) {
				p1.add(deck.get(i));
			}
			counter++;
		}
		else {
			for (int i = 39; i <52; i++) {
				p1.add(deck.get(i));
			}
			counter=0;
		}
		
	}	
	
	public String getHand(ArrayList<Card> hand) {
		String handList = "";
		int i = 1;
		for (Card c:hand)
		{
			handList += "\n" + i + "-" + c.toString();
			i++;
		}
		return handList;
	}
	
	
	public String toString()
	{
		String cardList = "";
		int i = 1;
		for (Card c:deck)
		{
			cardList += "\n" + i + "-" + c.toString();
			i++;
		}
		return cardList;
	}
}