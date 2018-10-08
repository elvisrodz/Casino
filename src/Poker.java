
public class Poker extends CardGame {
	pokerlookup checkhands;

	public Poker() {
		super.makeDeck(1);
	}

	public void DealFirst(Hand thehand) {
		for (int i = 0; i < 5; i++) {
			thehand.addCard(Hit());
		}

	}

	public int getValue(Hand thehand) {
		checkhands = new pokerlookup(thehand);
		switch (checkhands.returnvalues()) {
		case "Straight Flush":
			return 9;
		case "4":
			return 8;
		case "Full House":
			return 7;
		case "Flush":
			return 6;
		case "Straight":
			return 5;
		case "3":
			return 3;
		case "2 Pairs":
			return 2;
		case "2":
			return 1;
		case "none":
			return 0;
		}
		return 0;
	}

	public String ValueToString(Hand thehand) {
		checkhands = new pokerlookup(thehand);
		String Value = checkhands.FindHighestCard();
		switch (checkhands.returnvalues()) {
		case "4":
			return "Four of " + Value;
		case "3":
			return "Three of " + Value;
		case "2 Pairs":
			return "2 Pairs " + Value + " high";
		case "2":
			return "Pair of " + Value;
		case "none":
			return "High Card " + thehand.getCard(4).getValueAsString();
		default:
			return checkhands.returnvalues() + " " + Value + " high";
		}

	}

	public void playdealer(Hand thehand, boolean[] cardstodiscard) {
		checkhands = new pokerlookup(thehand);
		checkhands.dealerchecker(cardstodiscard);
	}

	public String FindWinner(Hand player, Hand dealer) {
		int pVal = getValue(player);
		int dVal = getValue(dealer);

		if (pVal > dVal) {
			return "Player Wins";
		} else if (pVal == dVal) {
			checkhands = new pokerlookup(player);
			int pHighC = checkhands.FindhighestValue();
			checkhands = new pokerlookup(dealer);
			int dHighC = checkhands.FindhighestValue();
			if (pHighC > dHighC) {
				return "Player Wins";
			} else if (pHighC < dHighC) {
				return "Dealer Wins";
			} else {
				return "Draw";
			}
		} else {
			return "Dealer Wins";
		}

	}


}
