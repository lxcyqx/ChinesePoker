package chinesepoker;

public class Card {
	private Suit suit;
	private Rank rank;
	
	/*
	 * suit = diamond, clubs, heart, spades
	 * rank = 3,4,5,6,7...Jack, Queen, King, Ace, 2
	 * value = 3,4,5,6,7...11,12,13,14,15
	 */
	
	enum Suit
	{
		DIAMONDS(1), CLUBS(2), HEARTS(3), SPADES(4);
		
		final int suitValue;
		private Suit(int sv)
		{
			suitValue = sv;
		}
		public int getSuitValue()
		{
			return suitValue;
		}
	}
	enum Rank{
		THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14), TWO(15);

		final int rankValue;
		private Rank(int v)
		{
			rankValue = v;
		}
		public int getRankValue() {
			return rankValue;
		}
	}

	public Card (Rank r, Suit s)
	{
		suit = s;
		rank = r;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public Rank getRank()
	{
		return rank;
	}

	/*
	 * Matches user-entered values to a card
	 */
	public static Card findCard(String c) {
		String value = c.substring(0,1);
		int numericVal = 0;
		switch (value) {
			case "3": numericVal = 0;
				break;
			case "4": numericVal = 1;
				break;
			case "5": numericVal = 2;
				break;
			case "6": numericVal = 3;
				break;
			case "7": numericVal = 4;
				break;
			case "8": numericVal = 5;
				break;
			case "9": numericVal = 6;
				break;
			case "t": numericVal = 7;
				break;	
			case "j": numericVal = 8;
				break;
			case "q": numericVal = 9;
				break;
			case "k": numericVal = 10;
				break;
			case "a": numericVal = 11;
				break;
			case "2": numericVal = 12;
				break;
			default: value = "Invalid value entered.";
				break;
		}
		
		String suitType = c.substring(1,2);
		int suitVal = 0;
		switch (suitType) {
			case "d": suitVal = 0;
				break;
			case "c": suitVal = 1;
				break;
			case "h": suitVal = 2;
				break;
			case "s": suitVal = 3;
				break;
			default:  suitType = "Invalid value entered.";
				break;
		}
		return new Card(Rank.values()[numericVal], Suit.values()[suitVal]);
	}
	
	public String toString()
	{
		return rank.toString() + " of " + suit.toString();
	}
	
}