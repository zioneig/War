/*Zion Eig-Tassiello, Period 3
This class represents a deck of cards, cards are represented with the card class
 */

import java.util.*;

public class Deck {
	private int numberInDeck;
	private Card[] myCards;

	public Deck(){
		myCards = new Card[52];
		Suit[] allSuits = Suit.values();
		Rank[] allRanks = Rank.values();

		int deckIndex = 0;

		//creates full deck of unique cards
		for(int suit = 0; suit < allSuits.length; suit++){
			for(int rank = 0; rank < allRanks.length; rank++){
				myCards[deckIndex] = new Card(allSuits[suit], allRanks[rank]);
				deckIndex++;
			}
		}

		numberInDeck = 52;
	}

	public Deck(int cardAmount){
		myCards = new Card[cardAmount];
	}

	public String toString(){
		String deck = "";

		for(int i = 0; i < numberInDeck; i++){
			deck += myCards[i];
			deck += "\n";
		}

		return deck;
	}

	public int numberInDeck(){
		return numberInDeck;
	}

	//returns true if the deck is currently empty
	public boolean isEmpty(){
		return (numberInDeck == 0);
	}

	//returns true if the deck is currently full
	public boolean isFull(){
		return (numberInDeck == myCards.length);
	}

	//assigns a new card to the bottom of the deck
	public void addToBottom(Card aCard){		
		if(isFull())
			throw new IllegalStateException("Deck is already full");

		if(isEmpty()){
			myCards[0] = new Card(aCard);
		}
		else{
			myCards[numberInDeck] = new Card(aCard);
		}

		numberInDeck++;
	}

	//removes and returns the top card of the deck
	public Card dealFromTop(){
		if(isEmpty())
			throw new NoSuchElementException("Deck is empty");

		Card top = myCards[0];

		for(int i = 0; i < numberInDeck-1; i++){
			myCards[i] = new Card(myCards[i+1]);
		}

		myCards[numberInDeck-1] = null;
		numberInDeck--;

		return top;
	}

	//shuffles the deck amount of times as specified
	public void shuffle(int numTimes){
		int index1;
		int index2;

		for(int i = 0; i < numTimes; i++){
			//ensures that the two random indexes are not equal
			do{
				index1 = (int)(Math.random()*numberInDeck);
				index2 = (int)(Math.random()*numberInDeck);
			}while(index1 == index2);

			//switches the two cards
			Card temp = new Card(myCards[index1]);
			myCards[index1] = new Card(myCards[index2]);
			myCards[index2] = new Card(temp);
		}
	}

	//reorganizes all cards in the deck 
	public void order(){
		int largest = 0;
		int lastCard = numberInDeck-1;

		//selection sorts each card based on card value
		for(int i = 0; i < numberInDeck; i++){
			for(int j = 0; j <= lastCard; j++){
				if(myCards[largest].compareTo(myCards[j]) < 0){
					largest = j;
				}
			}

			//swaps last card with current largest card
			Card temp = new Card(myCards[lastCard]);
			myCards[lastCard] = new Card(myCards[largest]);
			myCards[largest] = new Card(temp);

			largest = 0;
			lastCard--;
		}
	}

}
