package chinesepoker;

import java.util.ArrayList;

public class Smart_Player extends Hand implements Player{
	
	private String name = "Player 4";

	public Smart_Player(ArrayList<Card> h) {
		super(h);
	}

	public ArrayList<Card> play (ArrayList<Card> hand)
	{	
		ArrayList<Card> currentPlay = new ArrayList<Card>();
		findRoyalFlush(Game.p4);
		findStraightFlush(Game.p4);
		findBomb(Game.p4);
		findFullHouse(Game.p4);
		findFlush(Game.p4);
		findStraight(Game.p4);
		findDoubles(Game.p4);
		findSingles(Game.p4);
		
		if (straight.size()>0 && hasThreeOfDiamonds(straight))
		{
			currentPlay.add(straight.remove(0));
			currentPlay.add(straight.remove(0));
			currentPlay.add(straight.remove(0));
			currentPlay.add(straight.remove(0));
			currentPlay.add(straight.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		else if (flush.size()>0 && hasThreeOfDiamonds(flush))
		{
			currentPlay.add(flush.remove(0));
			currentPlay.add(flush.remove(0));
			currentPlay.add(flush.remove(0));
			currentPlay.add(flush.remove(0));
			currentPlay.add(flush.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		else if (fullHouse.size()>0 && hasThreeOfDiamonds(fullHouse))
		{
			currentPlay.add(fullHouse.remove(0));
			currentPlay.add(fullHouse.remove(0));
			currentPlay.add(fullHouse.remove(0));
			currentPlay.add(fullHouse.remove(0));
			currentPlay.add(fullHouse.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		else if (bomb.size()>0 && hasThreeOfDiamonds(bomb))
		{
			currentPlay.add(bomb.remove(0));
			currentPlay.add(bomb.remove(0));
			currentPlay.add(bomb.remove(0));
			currentPlay.add(bomb.remove(0));
			currentPlay.add(bomb.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		else if (royalFlush.size()>0 && hasThreeOfDiamonds(royalFlush))
		{
			currentPlay.add(royalFlush.remove(0));
			currentPlay.add(royalFlush.remove(0));
			currentPlay.add(royalFlush.remove(0));
			currentPlay.add(royalFlush.remove(0));
			currentPlay.add(royalFlush.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		else if (doubles.size()>0 && hasThreeOfDiamonds(doubles))
		{
			currentPlay.add(doubles.remove(0));
			currentPlay.add(doubles.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		else if (singles.size()>0 && hasThreeOfDiamonds(singles))
		{
			currentPlay.add(singles.remove(0));
			Game.setLastPlayed(currentPlay);
			return currentPlay;
		}
		
		//for the free play
				if (passCounter == Game.playersLeft-1)
				{
					passCounter=0;
					if (straight.size()>=5)
					{
							currentPlay.add(straight.remove(0));
							currentPlay.add(straight.remove(0));
							currentPlay.add(straight.remove(0));
							currentPlay.add(straight.remove(0));
							currentPlay.add(straight.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
					}
					
						if (flush.size()>=5)
						{
							currentPlay.add(flush.remove(0));
							currentPlay.add(flush.remove(0));
							currentPlay.add(flush.remove(0));
							currentPlay.add(flush.remove(0));
							currentPlay.add(flush.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;	
						}
						if (fullHouse.size()>=5)
						{
							currentPlay.add(fullHouse.remove(0));
							currentPlay.add(fullHouse.remove(0));
							currentPlay.add(fullHouse.remove(0));
							currentPlay.add(fullHouse.remove(0));
							currentPlay.add(fullHouse.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
						}
						if (bomb.size()>=5)
						{
							currentPlay.add(bomb.remove(0));
							currentPlay.add(bomb.remove(0));
							currentPlay.add(bomb.remove(0));
							currentPlay.add(bomb.remove(0));
							currentPlay.add(bomb.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
						}
						if (straightFlush.size()>=5)
						{
							currentPlay.add(straightFlush.remove(0));
							currentPlay.add(straightFlush.remove(0));
							currentPlay.add(straightFlush.remove(0));
							currentPlay.add(straightFlush.remove(0));
							currentPlay.add(straightFlush.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
						}
						if (royalFlush.size()>=5)
						{
							currentPlay.add(royalFlush.remove(0));
							currentPlay.add(royalFlush.remove(0));
							currentPlay.add(royalFlush.remove(0));
							currentPlay.add(royalFlush.remove(0));
							currentPlay.add(royalFlush.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
						}
						if (doubles.size()>=2)
						{
							currentPlay.add(doubles.remove(0));
							currentPlay.add(doubles.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
						}
						if (singles.size()>=1)
						{
							currentPlay.add(singles.remove(0));
							Game.setLastPlayed(currentPlay);
							if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
							{
								Game.win(Game.playerList.get(Game.currPlayer));
								return currentPlay;
							}
							return currentPlay;
						}
					}
		
		if (Game.lastPlayed.size()<=1)
		{
			//hand only has singles
			Hand.sortHand(singles);
			for (int i=0; i<singles.size(); i++)
			{
				if (canPlayOn(Game.lastPlayed, singles.get(i)))
				{
					passCounter=0;
					currentPlay.add(singles.remove(i));
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					Game.setLastPlayed(currentPlay);
					return currentPlay;
				}
			}
			pass();
			return currentPlay;
		}
			
		
		else if (Game.lastPlayed.size()==2)
		{
			if (doubles.size()>=2)
			{
				for (int i = 0; i<doubles.size()-1; i++)
				{
					if (canPlayOn(Game.lastPlayed, doubles.get(i), doubles.get(i+1)))
					{
						passCounter=0;
						currentPlay.add(doubles.remove(i+1));
						currentPlay.add(doubles.remove(i));
						Game.setLastPlayed(currentPlay);
						if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
						{
							Game.win(Game.playerList.get(Game.currPlayer));
							return currentPlay;
						}
						return currentPlay;
					}
				}
				pass();
				return currentPlay;
			}
			pass();
			return currentPlay;
		}
		
		else if (Game.lastPlayed.size()==5)
		{
			if (straight.size()>=5)
			{
				if (canPlayOn(Game.lastPlayed, straight.get(0), straight.get(1), straight.get(2), straight.get(3), straight.get(4)))
				{
					passCounter=0;
					currentPlay.add(straight.remove(0));
					currentPlay.add(straight.remove(0));
					currentPlay.add(straight.remove(0));
					currentPlay.add(straight.remove(0));
					currentPlay.add(straight.remove(0));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			if (flush.size()>=5)
			{
				if (canPlayOn(Game.lastPlayed, flush.get(0), flush.get(1), flush.get(2), flush.get(3), flush.get(4)))
				{
					passCounter=0;
					currentPlay.add(flush.remove(0));
					currentPlay.add(flush.remove(0));
					currentPlay.add(flush.remove(0));
					currentPlay.add(flush.remove(0));
					currentPlay.add(flush.remove(0));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			if (fullHouse.size()>=5)
			{
				if (canPlayOn(Game.lastPlayed, fullHouse.get(0), fullHouse.get(1), fullHouse.get(2), fullHouse.get(3), fullHouse.get(4)))
				{
					passCounter=0;
					currentPlay.add(fullHouse.remove(0));
					currentPlay.add(fullHouse.remove(0));
					currentPlay.add(fullHouse.remove(0));
					currentPlay.add(fullHouse.remove(0));
					currentPlay.add(fullHouse.remove(0));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			if (bomb.size()>=5)
			{
				if (canPlayOn(Game.lastPlayed, bomb.get(0), bomb.get(1), bomb.get(2), bomb.get(3), bomb.get(4)))
				{
					passCounter=0;
					currentPlay.add(bomb.remove(0));
					currentPlay.add(bomb.remove(0));
					currentPlay.add(bomb.remove(0));
					currentPlay.add(bomb.remove(0));
					currentPlay.add(bomb.remove(0));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			if (straightFlush.size()>=5)
			{
				if (canPlayOn(Game.lastPlayed, straightFlush.get(0), straightFlush.get(1), straightFlush.get(2), straightFlush.get(3), straightFlush.get(4)))
				{
					passCounter=0;
					currentPlay.add(straightFlush.remove(0));
					currentPlay.add(straightFlush.remove(0));
					currentPlay.add(straightFlush.remove(0));
					currentPlay.add(straightFlush.remove(0));
					currentPlay.add(straightFlush.remove(0));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			if (royalFlush.size()>=5)
			{
				if (canPlayOn(Game.lastPlayed, royalFlush.get(0), royalFlush.get(1), royalFlush.get(2), royalFlush.get(3), royalFlush.get(4)))
				{
					passCounter=0;
					currentPlay.add(royalFlush.remove(0));
					currentPlay.add(royalFlush.remove(0));
					currentPlay.add(royalFlush.remove(0));
					currentPlay.add(royalFlush.remove(0));
					currentPlay.add(royalFlush.remove(0));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(singles) && isEmpty(royalFlush) && isEmpty(straightFlush) && isEmpty(bomb) && isEmpty(fullHouse) && isEmpty(flush) && isEmpty(straight) && isEmpty(doubles)) 
					{
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			pass();
			return currentPlay;	
		}
		else 
		{
			pass();
			return currentPlay;
		}
}

		
	public ArrayList<Card> getIndex(int currPlayer){
		return null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString() {
		return name.toString();
	}
	
}