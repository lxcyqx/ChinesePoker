package chinesepoker;

import java.util.ArrayList;

public class Dumb_Player2 extends Hand implements Player{
	
	private String name = "Player 3";
	
	public Dumb_Player2(ArrayList<Card> h) {
		super(h);
	}

	public ArrayList<Card> getIndex(int currPlayer){
		return null;
	}
	public ArrayList<Card> play (ArrayList<Card> hand)
	{
		ArrayList<Card> currentPlay = new ArrayList<Card>();
		//if player has a free play
		if (passCounter == Game.playersLeft-1)
		{	
				currentPlay.add(hand.remove(0));
				passCounter=0;
				Game.setLastPlayed(currentPlay);
				if (isEmpty(hand)) 
				{
					Game.win(Game.playerList.get(Game.currPlayer));
					return currentPlay;
				}
				return currentPlay;
		}
		
		if (Game.lastPlayed.size() < 2) {
			for (int i=0; i<hand.size(); i++)
			{
				if (canPlayOn(Game.lastPlayed, hand.get(i)))
				{
					passCounter=0;
					currentPlay.add(hand.remove(i));
					Game.setLastPlayed(currentPlay);
					if (isEmpty(hand)) {
						Game.win(Game.playerList.get(Game.currPlayer));
						return currentPlay;
					}
					return currentPlay;
				}
			}
			pass();
			return currentPlay;
		}
		else if (Game.lastPlayed.size()==2)
		{
			for(int i = 0; i<hand.size()-1; i++) {
				if (isDouble(hand.get(i), hand.get(i+1))) {
					if (canPlayOn(Game.lastPlayed, hand.get(i), hand.get(i+1))) {	
						passCounter=0;
						currentPlay.add(hand.remove(i+1));
						currentPlay.add(hand.remove(i));
						Game.setLastPlayed(currentPlay);
						if (isEmpty(hand)) 
						{
							Game.win(Game.playerList.get(Game.currPlayer));
							return currentPlay;
						}
						return currentPlay;
					}
				}
			}
			pass();
			return currentPlay;
		}
		else if (Game.lastPlayed.size()==5)
		{
			if (hand.size()>=5)
			{
				for (int j = 0; j<hand.size()-4; j++)
				{
					if (hand.get(j).getRank().getRankValue()==hand.get(j+1).getRank().getRankValue() 
							&& hand.get(j+1).getRank().getRankValue() == hand.get(j+2).getRank().getRankValue())
					{
						if (hand.get(j+3).getRank().getRankValue()==hand.get(j+4).getRank().getRankValue())
						{
							if (canPlayOn(Game.lastPlayed, hand.get(j), hand.get(j+1),hand.get(j+2),hand.get(j+3),hand.get(j+4)))
							{
								passCounter=0;
								currentPlay.add(hand.remove(j+4));
								currentPlay.add(hand.remove(j+3));
								currentPlay.add(hand.remove(j+2));
								currentPlay.add(hand.remove(j+1));
								currentPlay.add(hand.remove(j));
								Game.setLastPlayed(currentPlay);
								if (isEmpty(hand)) 
								{
									Game.win(Game.playerList.get(Game.currPlayer));
									return currentPlay;
								}
								return currentPlay;
							}
						}
					}
				}
				pass();
				return currentPlay;
			}
				
			pass();
			return currentPlay;
		}
		pass();
		return currentPlay;
		}

	
	public String getName()
	{
		return name;
	}
	
	public String toString() {
		return name.toString();
	}
	
}