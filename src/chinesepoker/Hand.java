package chinesepoker;
import java.util.Arrays;
import java.util.ArrayList;

public class Hand{
	protected static int passCounter = 0;
	protected static int playersLeft = 4;

	static ArrayList<Card> cards = new ArrayList<Card>();
	
	static ArrayList<Card> royalFlush = new ArrayList<Card>();
	static ArrayList<Card> straightFlush = new ArrayList<Card>();
	static ArrayList<Card> bomb = new ArrayList<Card>();
	static ArrayList<Card> fullHouse = new ArrayList<Card>();
	static ArrayList<Card> flush = new ArrayList<Card>();
	static ArrayList<Card> straight = new ArrayList<Card>();
	static ArrayList<Card> doubles = new ArrayList<Card>();
//	static ArrayList<Card> triples = new ArrayList<Card>();
	static ArrayList<Card> singles = new ArrayList<Card>();

	public Hand(ArrayList<Card> h) {
		cards = h;
	}
	
	enum Combo
	{
		STRAIGHT(1), FLUSH(2), FULLHOUSE(3), BOMB(4), STRAIGHTFLUSH(5), ROYALFLUSH(6);
		
		final int comboValue;
		private Combo(int cv)
		{
			comboValue = cv;
		}
		public int getComboValue()
		{
			return comboValue;
		}
	}
	
	public void add(Card c) {
		cards.add(c);
	}
	
	public void printHand() {
		System.out.println("Printing Hand: ");
		for (int i=0; i<cards.size(); i++)
		{
			cards.get(i).toString();
		}
	}
	
	/*
	 * sorts the cards in hand in order of rank
	 */
	public static ArrayList<Card>sortHand(ArrayList<Card> hand) {
		for (int i = 0; i < hand.size(); i++) {
			for (int j=i+1; j<hand.size(); j++)
			{
				if (hand.get(i).getRank().getRankValue() > hand.get(j).getRank().getRankValue())
				{
					Card temp = hand.get(i);
					hand.set(i, hand.get(j));
					hand.set(j, temp);
				}
					
			}
		}
		for (int i = 0; i < hand.size(); i++) {
			for (int j=i+1; j<hand.size(); j++)
			{
				if (hand.get(i).getRank().getRankValue() == hand.get(j).getRank().getRankValue())
				{
					if (hand.get(i).getSuit().getSuitValue() > hand.get(j).getSuit().getSuitValue())
					{
						Card temp = hand.get(i);
						hand.set(i, hand.get(j));
						hand.set(j, temp);
					}
				}
					
			}
		}
		return hand;
	}
	
	/*
	 * determines whether the new play is greater than the last play
	 */
	public static boolean canPlayOn(ArrayList<Card> lastPlay, Card a)
	{
		if (lastPlay.size()==0)
		{
			return true;
		}
		if (passCounter==playersLeft-1)
		{
			passCounter=0;
			return true;
		}
		if (lastPlay.size()==1)
		{
			if (a.getRank().getRankValue() > lastPlay.get(0).getRank().getRankValue())
			{
				return true;
			}
			
			if (a.getRank().getRankValue() == lastPlay.get(0).getRank().getRankValue())
			{
				if (a.getSuit().getSuitValue() > lastPlay.get(0).getSuit().getSuitValue())
				{
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	public static boolean canPlayOn(ArrayList<Card>lastPlay, Card a, Card b)
	{
		if (isDouble(a, b))
		{
			if (lastPlay.size()==0)
			{
				return true;
			}
			if (passCounter==playersLeft-1)
			{
				passCounter=0;
				return true;
			}
			if (lastPlay.size()==2)
			{
				ArrayList<Card> newPlay = new ArrayList<Card>();
				newPlay.add(a); newPlay.add(b);
				
				//checks which of the two already played cards is greater
				Card largerPlayed = null; 
				if (lastPlay.get(0).getSuit().getSuitValue() > lastPlay.get(1).getSuit().getSuitValue())
				{
					largerPlayed = lastPlay.get(0);
				}
				else
				{
					largerPlayed = lastPlay.get(1);
				}
					
				//checks which of the new pair of cards is greater
				Card largerNew = null; 
				if (newPlay.get(0).getSuit().getSuitValue() > newPlay.get(1).getSuit().getSuitValue())
				{
					largerNew = newPlay.get(0);
				}
				else
				{
					largerNew = newPlay.get(1);
				}
					
				//checks to see that the new play is greater than previous play
				if (largerNew.getRank().getRankValue() > largerPlayed.getRank().getRankValue())
				{	
					return true; 
				}
				else if (largerNew.getRank().getRankValue() == largerPlayed.getRank().getRankValue())
				{
					if (largerNew.getSuit().getSuitValue() > largerPlayed.getSuit().getSuitValue())
					{
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	public static boolean canPlayOn(ArrayList<Card>lastPlay, Card a, Card b, Card c, Card d, Card e)
	{
		if (isStraight(a, b, c, d, e) || isFlush(a, b, c, d, e) || isFullHouse (a, b, c, d, e) || isBomb (a, b, c, d, e))
		{
			if (lastPlay.size()==0)
			{
				return true;
			}
			if (passCounter==playersLeft-1)
			{
				passCounter=0;
				return true;
			}
			
			if (lastPlay.size()==5)
			{
				ArrayList<Card> newPlay = new ArrayList<Card>();
				newPlay.add(a); newPlay.add(b); newPlay.add(c); newPlay.add(d); newPlay.add(e);
				sortHand(newPlay);
				//if the combo value of the new play is greater than the combo value of the previous play, return true
				if (getCombo(newPlay).getComboValue() > getCombo(lastPlay).getComboValue())
				{
					return true;
				}
				
				//if the new combo and old combo are both full house, find the largest card in the triples and compare which is greater
				else if (getCombo(newPlay)==Combo.FULLHOUSE && getCombo(lastPlay)==Combo.FULLHOUSE)
				{
					Card largestOfNewTriple = newPlay.get(0);
					Card largestOfOldTriple = lastPlay.get(0);
					if (isDouble(newPlay.get(0), newPlay.get(1)))
					{
						largestOfNewTriple = newPlay.get(2);
					}
					if (isDouble(lastPlay.get(0), lastPlay.get(1)))
					{
						largestOfOldTriple = lastPlay.get(2);
					}
					if (largestOfNewTriple.getRank().getRankValue() > largestOfOldTriple.getRank().getRankValue())
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				
				//if the new combo and old combo are both bombs, find the largest card in the quadruples and compare which is greater
				else if (getCombo(newPlay)==Combo.BOMB && getCombo(lastPlay)==Combo.BOMB)
				{
					Card largestOfNewQuadruple = newPlay.get(0);
					Card largestOfOldQuadruple = lastPlay.get(0);
					if (isDouble(newPlay.get(0), newPlay.get(1)))
					{
						largestOfNewQuadruple = newPlay.get(4);
					}
					if (isDouble(lastPlay.get(0), lastPlay.get(1)))
					{
						largestOfOldQuadruple = lastPlay.get(4);
					}
					if (largestOfNewQuadruple.getRank().getRankValue() > largestOfOldQuadruple.getRank().getRankValue())
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				//if the new combo and old combo are both flush, the one with the greater suit is greater
				//if both have the same suit, the one with the highest value is greater
				else if (getCombo(newPlay)==Combo.FLUSH && getCombo(lastPlay)==Combo.FLUSH)
				{
					if (newPlay.get(0).getSuit().getSuitValue() > lastPlay.get(0).getSuit().getSuitValue())
					{
						return true;
					}
					else if (newPlay.get(0).getSuit().getSuitValue() == lastPlay.get(0).getSuit().getSuitValue())
					{
						Card largestNew = newPlay.get(0);
						for (int i=0; i<newPlay.size(); i++)
						{
							if (newPlay.get(i).getRank().getRankValue() >= largestNew.getRank().getRankValue() && newPlay.get(i).getSuit().getSuitValue() > largestNew.getSuit().getSuitValue())
							{
								largestNew = newPlay.get(i);
							}
						}
						Card largestOld = lastPlay.get(0);
						for (int i=0; i<lastPlay.size(); i++)
						{
							if (lastPlay.get(i).getRank().getRankValue() >= largestOld.getRank().getRankValue() && lastPlay.get(i).getSuit().getSuitValue() > largestOld.getSuit().getSuitValue())
							{
								largestOld = lastPlay.get(i);
							}
						}
						if (largestNew.getRank().getRankValue() > largestOld.getRank().getRankValue())
						{
							return true;
						}
						else
						{
							return false;
						}
					}
					else
					{
						return false;
					}
				}
				//for straight, if the combo value of the new play is equal to the combo value of the previous play, find the greatest card in each combo and compare
				else if (getCombo(newPlay).getComboValue() == getCombo(lastPlay).getComboValue())
				{
					Card largestNew = newPlay.get(0);
					for (int i=0; i<newPlay.size(); i++)
					{
						if (newPlay.get(i).getRank().getRankValue() >= largestNew.getRank().getRankValue() && newPlay.get(i).getSuit().getSuitValue() > largestNew.getSuit().getSuitValue())
						{
							largestNew = newPlay.get(i);
						}
					}
					
					Card largestOld = lastPlay.get(0);
					for (int i=0; i<lastPlay.size(); i++)
					{
						if (lastPlay.get(i).getRank().getRankValue() >= largestOld.getRank().getRankValue() && lastPlay.get(i).getSuit().getSuitValue() > largestOld.getSuit().getSuitValue())
						{
							largestOld = lastPlay.get(i);
						}
					}
					
					if (largestNew.getRank().getRankValue() > largestOld.getRank().getRankValue())
					{
						return true;
					}
					else if (largestNew.getRank().getRankValue()==largestOld.getRank().getRankValue())
					{
						if (largestNew.getSuit().getSuitValue()>largestOld.getSuit().getSuitValue())
						{
							return true;
						}
						else
						{
							return false;
						}
					}
					return false;
				}
			}
			return false;
		}
		return false;
	}

	/*
	 * checks the combination of cards played
	 */
	public static boolean isDouble(Card a, Card b)
	{
		if (a.getRank().getRankValue()==b.getRank().getRankValue())
		{
			return true;
		}
		return false;
	}
	
	public static boolean isStraight(Card a, Card b, Card c, Card d, Card e)
	{
		int[] straightCheck = {a.getRank().getRankValue(), b.getRank().getRankValue(), c.getRank().getRankValue(), d.getRank().getRankValue(), e.getRank().getRankValue()};
		Arrays.sort(straightCheck);
		for (int i = 0; i<straightCheck.length-1; i++)
		{
			if (straightCheck[i] != straightCheck[i+1]-1)
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean isFlush(Card a, Card b, Card c, Card d, Card e)
	{
		if (a.getSuit().getSuitValue() == b.getSuit().getSuitValue() 
				&& b.getSuit().getSuitValue() == c.getSuit().getSuitValue() 
				&& c.getSuit().getSuitValue() == d.getSuit().getSuitValue() 
				&& d.getSuit().getSuitValue()== e.getSuit().getSuitValue())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isFullHouse(Card a, Card b, Card c, Card d, Card e)
	{
		int[] straightCheck = {a.getRank().getRankValue(), b.getRank().getRankValue(), c.getRank().getRankValue(), d.getRank().getRankValue(), e.getRank().getRankValue()};
		Arrays.sort(straightCheck);
		if (straightCheck[0] == straightCheck[1] && straightCheck[1]==straightCheck[2])
		{
			if (straightCheck[3] == straightCheck[4])
			{
				return true;
			}
		}
		else if (straightCheck[0] == straightCheck[1])
		{
			if (straightCheck[2] == straightCheck[3] && straightCheck[3] == straightCheck[4])
			{
				return true;
			}
		}
		return false;
		
	}
	
	public static boolean isBomb(Card a, Card b, Card c, Card d, Card e)
	{
		int[] straightCheck = {a.getRank().getRankValue(), b.getRank().getRankValue(), c.getRank().getRankValue(), d.getRank().getRankValue(), e.getRank().getRankValue()};
		Arrays.sort(straightCheck);
		if (straightCheck[0] == straightCheck[1] && straightCheck[1]==straightCheck[2] && straightCheck[2]==straightCheck[3])
		{
			return true;
		}
		else if (straightCheck[1] == straightCheck[2] && straightCheck[2]==straightCheck[3] && straightCheck[3]==straightCheck[4])
		{
			return true;
		}
		return false;
		
	}
	
	public static boolean isStraightFlush(Card a, Card b, Card c, Card d, Card e)
	{
		if (isStraight(a, b, c, d, e) && isFlush(a, b, c, d, e))
		{
			return true;
		}
		return false;		
	}
	
	public static boolean isRoyalFlush(Card a, Card b, Card c, Card d, Card e)
	{
		int[] cards = {a.getRank().getRankValue(), b.getRank().getRankValue(), c.getRank().getRankValue(), d.getRank().getRankValue(), e.getRank().getRankValue()};
		int counter=0;
		if (isFlush(a, b, c, d, e))
		{
			for (int i = 0; i<cards.length; i++)
			{
				if (cards[i]>=11)
				{
					counter++;
				}
			}
		}
		if (counter==5)
		{
			return true;
		}
		return false;
	}
	
	public static boolean isThreeOfAKind(Card a, Card b, Card c)
	{
		int[] straightCheck = {a.getRank().getRankValue(), b.getRank().getRankValue(), c.getRank().getRankValue()};
		if (straightCheck[0] == straightCheck[1] && straightCheck[1]==straightCheck[2])
		{
			return true;
		}
		return false;
	}
	
	public static boolean hasThreeOfDiamonds(ArrayList<Card> h)
	{
		for (int i=0; i<h.size(); i++)
		{
			if (h.get(i).getRank().getRankValue()==3 && h.get(i).getSuit().getSuitValue()==1)
			{
				return true;
			}
		}
		return false;
	}
	
	public static void pass()
	{
		passCounter++;
		System.out.println(Game.playerList.get(Game.currPlayer) + " passes");
	}
	
	public static Combo getCombo(ArrayList<Card> combo)
	{
		if (isRoyalFlush(combo.get(0), combo.get(1), combo.get(2), combo.get(3), combo.get(4)))
		{
			return Combo.ROYALFLUSH;
		}
		else if (isStraightFlush(combo.get(0), combo.get(1), combo.get(2), combo.get(3), combo.get(4)))
		{
			return Combo.STRAIGHTFLUSH;
		}
		else if (isStraight(combo.get(0), combo.get(1), combo.get(2), combo.get(3), combo.get(4)))
		{
			return Combo.STRAIGHT;
		}
		else if (isFlush(combo.get(0), combo.get(1), combo.get(2), combo.get(3), combo.get(4)))
		{
			return Combo.FLUSH;
		}
		else if (isFullHouse(combo.get(0), combo.get(1), combo.get(2), combo.get(3), combo.get(4)))
		{
			return Combo.FULLHOUSE;
		}
		else if (isBomb(combo.get(0), combo.get(1), combo.get(2), combo.get(3), combo.get(4)))
		{
			return Combo.BOMB;
		}
		else
		{
			return null;
		}
		
	}
	
	public static void findRoyalFlush(ArrayList<Card> hand)
	{
		int royalDiamonds = 0;
		int royalClubs = 0;
		int royalHearts = 0;
		int royalSpades = 0;
		
		for (int i=0; i<hand.size(); i++)
		{
			if (hand.get(i).getRank().getRankValue()>=11 && hand.get(i).getRank().getRankValue()<=15)
			{
				if (hand.get(i).getSuit().getSuitValue()==1)
				{
					royalDiamonds++;
				}
				else if (hand.get(i).getSuit().getSuitValue()==2)
				{
					royalClubs++;
				}
				else if (hand.get(i).getSuit().getSuitValue()==3)
				{
					royalHearts++;
				}
				else 
				{
					royalSpades++;
				}
			}
		}
		
		if (royalSpades==5)
		{
			for (int i=hand.size()-1; i>0; i--)	
			{
				if(hand.get(i).getSuit().getSuitValue()==4 && hand.get(i).getRank().getRankValue()>=11 && hand.get(i).getRank().getRankValue()<=15)
				{
					Card card = hand.get(i);
					royalFlush.add(card);
					hand.remove(i);
				}
			}
		}
		if (royalHearts==5)
		{
			for (int i=hand.size()-1; i>0; i--)	
			{
				if(hand.get(i).getSuit().getSuitValue()==3 && hand.get(i).getRank().getRankValue()>=11 && hand.get(i).getRank().getRankValue()<=15)
				{
					Card card = hand.get(i);
					royalFlush.add(card);
					hand.remove(i);
				}
			}
		}
		if (royalClubs==5)
		{
			for (int i=hand.size()-1; i>0; i--)	
			{
				if(hand.get(i).getSuit().getSuitValue()==2 && hand.get(i).getRank().getRankValue()>=11 && hand.get(i).getRank().getRankValue()<=15)
				{
					Card card = hand.get(i);
					royalFlush.add(card);
					hand.remove(i);
				}
			}
		}
		if (royalDiamonds==5)
		{
			for (int i=hand.size()-1; i>0; i--)	
			{
				if(hand.get(i).getSuit().getSuitValue()==1 && hand.get(i).getRank().getRankValue()>=11 && hand.get(i).getRank().getRankValue()<=15)
				{
					Card card = hand.get(i);
					royalFlush.add(card);
					hand.remove(i);
				}
			}
		}
	}
	
	static int diamonds = 0;
	static int clubs = 0;
	static int hearts = 0;
	static int spades = 0;
	
	public static void findStraightFlush(ArrayList<Card> hand)
	{
		ArrayList<Card> flush = new ArrayList<Card>();
		int clubs = 0;
		int diamonds = 0;
		int hearts = 0;
		int spades = 0;
		for (int i=0; i<hand.size(); i++)
		{
			if (hand.get(i).getSuit().getSuitValue()==1)
			{
				diamonds++;
			}
			else if (hand.get(i).getSuit().getSuitValue()==2)
			{
				clubs++;
			}
			else if (hand.get(i).getSuit().getSuitValue()==3)
			{
				hearts++;
			}
			else 
			{
				spades++;
			}
		}

		
		int diamondCounter = hand.size()-1;

		while (diamonds>0)
		{
			if (hand.get(diamondCounter).getSuit().getSuitValue()==1) {
				Card card = hand.get(diamondCounter);
				flush.add(card);
				hand.remove(diamondCounter);
				diamonds--;
			}
			diamondCounter--;
		}
		
		int clubsCounter = hand.size()-1;
		while (clubs>0)
		{
			if (hand.get(clubsCounter).getSuit().getSuitValue()==2) {
				Card card = hand.get(clubsCounter);
				flush.add(card);
				hand.remove(clubsCounter);
				clubs--;
			}
			clubsCounter--;
		}
		
		int heartsCounter = hand.size()-1;
		while (hearts>0)
		{
			if (hand.get(heartsCounter).getSuit().getSuitValue()==3) {
				Card card = hand.get(heartsCounter);
				flush.add(card);
				hand.remove(heartsCounter);
				hearts--;
			}
			heartsCounter--;
		}
		
		int spadesCounter = hand.size()-1;
		while (spades>0)
		{
			if (hand.get(spadesCounter).getSuit().getSuitValue()==4) {
				Card card = hand.get(spadesCounter);
				flush.add(card);
				hand.remove(spadesCounter);
				spades--;
			}
			spadesCounter--;
		}
		Hand.sortHand(hand);
		
		if (flush.size()>=5)
		{
			for (int i = 0; i<flush.size()-4; i++) {
				if (flush.get(i).getRank().getRankValue() -1 == flush.get(i+1).getRank().getRankValue()
						&&flush.get(i+1).getRank().getRankValue() -1 == flush.get(i+2).getRank().getRankValue()
						&&flush.get(i+2).getRank().getRankValue() -1 == flush.get(i+3).getRank().getRankValue()
						&&flush.get(i+3).getRank().getRankValue() -1 == flush.get(i+4).getRank().getRankValue()) {
					straightFlush.add(flush.remove(i+4));
					straightFlush.add(flush.remove(i+3));
					straightFlush.add(flush.remove(i+2));
					straightFlush.add(flush.remove(i+1));
					straightFlush.add(flush.remove(i));
					while(flush.size()%5!=0) {
						hand.add(flush.remove(0));
					}
				}	
			}
			while (flush.size()>0) {
				hand.add(flush.remove(0));

			}
		}
	}
	
	public static void findBomb(ArrayList<Card> hand)
	{
		for (int i=0; i<hand.size()-3; i++)
		{
			if (hand.get(i).getRank().getRankValue()==hand.get(i+1).getRank().getRankValue() 
					&& hand.get(i+1).getRank().getRankValue() == hand.get(i+2).getRank().getRankValue()
					&& hand.get(i+2).getRank().getRankValue() == hand.get(i+3).getRank().getRankValue())
			{
				bomb.add(hand.remove(i+3));
				bomb.add(hand.remove(i+2));
				bomb.add(hand.remove(i+1));
				bomb.add(hand.remove(i));
				bomb.add(hand.remove(0));
			}
		}	
	}
	
	public static void findFullHouse(ArrayList<Card> hand)
	{
		for (int i=0; i<hand.size()-2; i++)
		{
			if (hand.get(i).getRank().getRankValue()==hand.get(i+1).getRank().getRankValue() 
					&& hand.get(i+1).getRank().getRankValue() == hand.get(i+2).getRank().getRankValue())
			{
				fullHouse.add(hand.remove(i+2));
				fullHouse.add(hand.remove(i+1));
				fullHouse.add(hand.remove(i));
				for(int j =0; j<hand.size()-1; j++) {
					if (hand.get(j).getRank().getRankValue()==hand.get(j+1).getRank().getRankValue()) {
						fullHouse.add(hand.remove(j+1));
						fullHouse.add(hand.remove(j));
						break;
					}
				}
			break;
			}
		}
	}
	
	public static void findFlush(ArrayList<Card> hand)
	{
		int clubs = 0;
		int diamonds = 0;
		int hearts = 0;
		int spades = 0;
		for (int i=0; i<hand.size(); i++)
		{
			if (hand.get(i).getSuit().getSuitValue()==1)
			{
				diamonds++;
			}
			else if (hand.get(i).getSuit().getSuitValue()==2)
			{
				clubs++;
			}
			else if (hand.get(i).getSuit().getSuitValue()==3)
			{
				hearts++;
			}
			else 
			{
				spades++;
			}
		}

		
		int diamondCounter = hand.size()-1;
		while (diamonds>0)
		{
			if (hand.get(diamondCounter).getSuit().getSuitValue()==1) {
				Card card = hand.get(diamondCounter);
				flush.add(card);
				hand.remove(diamondCounter);
				diamonds--;
			}
			diamondCounter--;
		}
		while(flush.size()%5!=0)
		{
			hand.add(flush.remove(flush.size()-1));
		}
		
		int clubsCounter = hand.size()-1;
		while (clubs>0)
		{
			if (hand.get(clubsCounter).getSuit().getSuitValue()==2) {
				Card card = hand.get(clubsCounter);
				flush.add(card);
				hand.remove(clubsCounter);
				clubs--;
			}
			clubsCounter--;
		}
		while(flush.size()%5!=0)
		{
			hand.add(flush.remove(flush.size()-1));
		}
		
		int heartsCounter = hand.size()-1;
		while (hearts>0)
		{
			if (hand.get(heartsCounter).getSuit().getSuitValue()==3) {
				Card card = hand.get(heartsCounter);
				flush.add(card);
				hand.remove(heartsCounter);
				hearts--;
			}
			heartsCounter--;
		}
		while(flush.size()%5!=0)
		{
			hand.add(flush.remove(flush.size()-1));
		}
		
		int spadesCounter = hand.size()-1;
		while (spades>0)
		{
			if (hand.get(spadesCounter).getSuit().getSuitValue()==4) {
				Card card = hand.get(spadesCounter);
				flush.add(card);
				hand.remove(spadesCounter);
				spades--;
			}
			spadesCounter--;
		}
		while(flush.size()%5!=0)
		{
			hand.add(flush.remove(flush.size()-1));
		}
		sortHand(hand);
	}
	
	public static void findStraight(ArrayList<Card> hand)
	{
		for (int i = 0; i <hand.size()-4; i++) {
			if ((hand.get(i+1).getRank().getRankValue() - hand.get(i).getRank().getRankValue() == 1)
				&& (hand.get(i+2).getRank().getRankValue() - hand.get(i+1).getRank().getRankValue() == 1)
				&& (hand.get(i+3).getRank().getRankValue() - hand.get(i+2).getRank().getRankValue() == 1)
				&& (hand.get(i+4).getRank().getRankValue() - hand.get(i+3).getRank().getRankValue() == 1)) {
				straight.add(hand.remove(i+4));
				straight.add(hand.remove(i+3));
				straight.add(hand.remove(i+2));
				straight.add(hand.remove(i+1));
				straight.add(hand.remove(i));
			}
		}
	}
	public static void findDoubles(ArrayList<Card> hand)
	{
		for (int i=0; i<hand.size()-1; i++)
		{
			if (isDouble(hand.get(i), hand.get(i+1)))
			{
				doubles.add(hand.remove(i+1));
				doubles.add(hand.remove(i));
			}
		}
	}
	public static void findSingles(ArrayList<Card> hand)
	{
		for (Card c:hand)
		{
			singles.add(c);
		}
		hand.clear();
	}
	
	public static boolean isEmpty(ArrayList<Card> hand)
	{
		if (hand.size()==0)
		{
			return true;
		}
		return false;
	}	
}