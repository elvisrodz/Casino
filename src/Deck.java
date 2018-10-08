
public class Deck {
	private Card[] deck;

	private int cardsUsed;

	public Deck(boolean includeJokers, int noOfDecks) {
		if (includeJokers)
			deck = new Card[(54 * noOfDecks)];
		else
			deck = new Card[(52 * noOfDecks)];

		int cardsadded = 0;
		for (int decks = 1; decks <= noOfDecks; decks++) {
			for (int suit = 1; suit <= 4; suit++) {
				for (int value = 1; value <= 13; value++) {
					deck[cardsadded] = new Card(value, suit, getname(value, suit));
					cardsadded++;
				}
			}
		}
		if (includeJokers) {
			deck[deck.length - 2] = new Card(1, 0, "red_joker");
			deck[deck.length - 1] = new Card(2, 0, "black_joker");

		}
		Shuffle();
	}

	public int cardsLeft() {
		return deck.length - cardsUsed;
	}

	public void Shuffle() {
		for (int i = 0; i <= deck.length - 1; i++) {
			int rand = (int) (Math.random() * (i + 1));
			Card temp = deck[rand];
			deck[rand] = deck[i];
			deck[i] = temp;
		}

		cardsUsed = 0;
	}

	public void printall() {
		for (int i = 0; i <= deck.length - 1; i++) {
			System.out.println(deck[i].toString() + " & " + deck[i].getPath());

		}
	}

	public Card dealCard() {
		cardsUsed++;
		if (cardsLeft() == 0) {
			cardsUsed = 1;
		}
		return deck[cardsUsed - 1];

	}

	public String getname(int value, int suit) {

		String valueS;
		String suitS = null;
		switch (value) {
		case 1:
			valueS = "Ace";
			break;
		case 2:
			valueS = "2";
			break;
		case 3:
			valueS = "3";
			break;
		case 4:
			valueS = "4";
			break;
		case 5:
			valueS = "5";
			break;
		case 6:
			valueS = "6";
			break;
		case 7:
			valueS = "7";
			break;
		case 8:
			valueS = "8";
			break;
		case 9:
			valueS = "9";
			break;
		case 10:
			valueS = "10";
			break;
		case 11:
			valueS = "Jack";
			break;
		case 12:
			valueS = "Queen";
			break;
		default:
			valueS = "King";
			break;
		}
		switch (suit) {
		case 1:
			suitS = "Hearts";
			break;
		case 2:
			suitS = "Diamonds";
			break;
		case 3:
			suitS = "Clubs";
			break;
		case 4:
			suitS = "Spades";
			break;
		}

		return valueS.toLowerCase() + "_of_" + suitS.toLowerCase();
	}

}
