/*Zion Eig-Tassiello, Period 3
This class represents a playing card
 */

public class Card {

	private Suit suit;
	private Rank rank;
	private boolean faceup;
	
	public Card(Suit s, Rank r){
		suit = s;
		rank = r;
		
		faceup = true;
	}
	
	public Card(Card other){
		suit = other.suit;
		rank = other.rank;
		
		faceup = other.faceup;
	}
	
	public String toString(){
		if(faceup){
			return rank + " of " + suit;
		}
		else{
			return "unknown";
		}
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public boolean isCardUp(){
		return faceup;
	}
	
	//flips the card
	public void flip(){
		faceup = !faceup;
	}

	//returns value of card [0, 51]
	public int cardValue(){
		return ((suit.ordinal()+1) * 13) + rank.ordinal();
	}
	
	//compares two card's values
	public int compareTo(Card other){
		return cardValue()-other.cardValue();
	}
	
	//compares two cards and returns true if they are equals
	public boolean equals(Card other){
		if(suit == other.suit && rank == other.rank){
			return true;
		}
		
		return false;
	}
}
