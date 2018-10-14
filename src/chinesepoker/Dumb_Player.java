package chinesepoker;

import java.util.ArrayList;

public class Dumb_Player extends Hand implements Player{
	
	private String name = "Player 2";

	public Dumb_Player(ArrayList<Card> h) {
		super(h);
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
			for(int i =0; i<hand.size()-1; i++) {
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
		pass();
		return currentPlay;
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