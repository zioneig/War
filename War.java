/*Zion Eig-Tassiello, Period 3
This class represents the game of War!
 */
import java.util.*;

public class War {

	private Deck player;
	private Deck computer;
	
	public War(){
		Deck startDeck = new Deck();
		startDeck.shuffle(100);
		
		player = new Deck(52);
		computer = new Deck(52);
		
		//deals cards to the player and computer's deck
		do{
			player.addToBottom(startDeck.dealFromTop());
			computer.addToBottom(startDeck.dealFromTop());
			
		}while(!startDeck.isEmpty());
		
	}
	
	public void playGame(){
		System.out.println("Let's play war.");
		System.out.print("What is your name:");
		
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		
		System.out.format("%-10s %-20s %-20s %2s", "Round", name, "Computer", "Left");
		System.out.println();
		System.out.format("%-10s %-20s %-20s %2s", "-----", "------", "--------", "----");
		System.out.println();
		
		int round = 1;
		
		//goes through each round of war until either the player or computer loses all their cards
		do{
			checkWinner(computer.dealFromTop(), player.dealFromTop(), round);
			round++;
			
		}while(!player.isEmpty() && !computer.isEmpty());
		
		System.out.println();
		System.out.println("Gameover");
		
		if(player.isEmpty())
			System.out.println("The computer wins!");
		
		else
			System.out.println(name + " wins, Congratulations!");
	}
	
	//determines the winner between two cards and updates the appropriate deck
	public void checkWinner(Card computerCard, Card playerCard, int round){
		//carries out war functionality
		if(playerCard.getRank().ordinal() == computerCard.getRank().ordinal()){
			System.out.format("%-10d %-20s %-20s %2s", round, playerCard, computerCard, "War!");
			System.out.println();
			playWar(computerCard, playerCard, round);
			
			return;			
		}
		//if the player has won this round
		else if(computerCard.getRank().ordinal() < playerCard.getRank().ordinal()){
			player.addToBottom(playerCard);
			player.addToBottom(computerCard);
		}
		//if the computer has won this round
		else{
			computer.addToBottom(computerCard);
			computer.addToBottom(playerCard);
		}
		
		displayRound(round, playerCard, computerCard);
	}
	
	//each player discards 3 cards and the 4th card is compared, the round is then displayed
	public void playWar(Card computerCard, Card playerCard, int round){
		if(player.numberInDeck() == 0 || computer.numberInDeck() == 0){
			return; 
		}
		
		Deck warDeck = new Deck(10);
		warDeck.addToBottom(computerCard);
		warDeck.addToBottom(playerCard);
		
		int playerDiscard = 3;
		int computerDiscard = 3;
		
		//adjusts discard amount depending on the amount in each deck
		if(player.numberInDeck() < 4)
			playerDiscard = player.numberInDeck()-1;
		
		if(computer.numberInDeck() < 4)
			computerDiscard = computer.numberInDeck()-1;
		
		//discards cards into the warDeck
		moveCards(warDeck, player, playerDiscard);
		moveCards(warDeck, computer, computerDiscard);
		
		//pulls fourth card from each deck which are then compared
		Card playerCompare = player.dealFromTop();
		Card computerCompare = computer.dealFromTop();
		
		warDeck.addToBottom(playerCompare);
		warDeck.addToBottom(computerCompare);

		//compares final card
		if(computerCompare.getRank().ordinal() < playerCompare.getRank().ordinal())
			moveCards(player, warDeck);

		else
			moveCards(computer, warDeck);

		
		displayRound(round, playerCompare, computerCompare);
	}
	
	//moves all the cards from one deck into another
	private void moveCards(Deck fill, Deck add){
		int cardAmount = add.numberInDeck();
		
		for(int i = 0; i < cardAmount; i++){
			fill.addToBottom(add.dealFromTop());
		}
	}
	
	//moves a certain amount of cards from one deck into another
	private void moveCards(Deck fill, Deck add, int cardAmount){		
		for(int i = 0; i < cardAmount; i++){
			fill.addToBottom(add.dealFromTop());
		}
	}
	
	//displays the current round and how many cards the player has left
	private void displayRound(int round, Card playerCard, Card computerCard){
		System.out.format("%-10d %-20s %-20s %2d", round, playerCard, computerCard, player.numberInDeck());
		System.out.println();
	}
	
	public static void main(String[] args){
		War game = new War();
		
		game.playGame();
	}
}
