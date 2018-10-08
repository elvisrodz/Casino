public class Blackjack extends CardGame {

	double thecount;

	public Blackjack() {
		super.makeDeck(6);
	}

	public Card Hit(Hand theHand) {
		Card temp;
		temp = super.Hit();
		CountUpdate(temp.getValue());
		return temp;
	}

	public void DealFirst(Hand playerhand, Hand dealerhand) {
		playerhand.addCard(Hit());
		playerhand.addCard(Hit());
		dealerhand.addCard(Hit());
		dealerhand.addCard(deck.dealCard());// hidden card
	}

	public void DealerTurn(Hand dealerhand) {
		while (dealerhand.getTotalValue() <= 16 && dealerhand.getTotalValue() > 0) {
			dealerhand.addCard(Hit(dealerhand));
		}

	}

	public boolean checkBlackJack(Hand theHand) {
		if (theHand.getTotalValue() == 21) {
			return true;
		} else {
			return false;
		}

	}

	public boolean Bust(Hand theHand) {
		if (theHand.getTotalValue() == 0) {
			return true;

		} else {
			return false;
		}
	}

	public String FindWinner(Hand playerhand, Hand dealerhand) {
		if (playerhand.getTotalValue() > dealerhand.getTotalValue()) {
			return "Player wins";
		} else {
			return "Dealer wins";

		}
	}

	public void CountUpdate(int value) {

		// Aces are tens are worth -1.
		if (value >= 10 || value == 1) {
			thecount -= 1;
		} else if (value == 9) {
			// Nines are worth -0.5.
			thecount -= 0.5;
		} else if (value >= 3 && value <= 6) {
			// Threes, Fours, and Sixes are worth +1.
			thecount += 0.5;
		} else if (value == 5) {
			// Fives are worth +1.5.
			thecount += 1.5;
		} else {
			// Deuces and Sevens are worth +0.5.
			thecount += 0.5;
		}

	}

	public void Shuffle() {
		super.Shuffle();
		thecount = 0;
	}

	public String getCount() {
		double truecount;
		truecount = (thecount / 6);
		double roundOff = (double) Math.round(truecount * 100) / 100;
		return Double.toString(roundOff);
	}
}
