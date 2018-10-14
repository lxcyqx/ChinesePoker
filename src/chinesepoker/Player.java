package chinesepoker;

import java.util.ArrayList;

public interface Player {
	ArrayList<Card> lastPlayed = new ArrayList<Card>();
	ArrayList<Card> player1 = new ArrayList<Card>();
	ArrayList<Card> player2 = new ArrayList<Card>();
	ArrayList<Card> player3 = new ArrayList<Card>();
	ArrayList<Card> player4 = new ArrayList<Card>();
	ArrayList<Card> play(ArrayList<Card> h);
	ArrayList<Card> getIndex(int currPlayer);
}
