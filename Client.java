
public class Client {

	public static void main(String[] args){
		
		Card card1 = new Card(Suit.CLUB, Rank.TWO);
		Card card2 = new Card(Suit.CLUB, Rank.TWO);
		
		System.out.println(card1.compareTo(card2));
	}
}
