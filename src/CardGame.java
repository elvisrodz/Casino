import javax.swing.JOptionPane;

public abstract class CardGame {
	Deck deck;
	public int GetHand(Hand theHand) {
		return theHand.getTotalValue();

	}

	public Card Hit() {
		return deck.dealCard();
	}

	public void makeDeck(int noOfDecks) {
		deck= new Deck(false,noOfDecks);
	}

	public String ChangeBet() {
		String bet = (String) JOptionPane.showInputDialog(null, "Enter New Bet", "Change Bet",
				JOptionPane.OK_CANCEL_OPTION, null, null, "100");

		return bet;
		// fix this
	}
	
	public void Shuffle() {
		
		deck.Shuffle();
		
	}
}
