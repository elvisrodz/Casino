
public class Card {
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;
	public final static int SPADES = 4;

	public int JOKER = 0;

	public int ACE = 1;
	public int JACK = 11;
	public int QUEEN = 12;
	public int KING = 13;

	private final int suit;
	private final int value;
	private final String path;
	// add validation for cards here

	public Card(int value2, int suit2, String path2) {
		if (suit2 != SPADES && suit2 != HEARTS && suit2 != DIAMONDS && suit2 != CLUBS)
			throw new IllegalArgumentException("Illegal playing card suit");
		if (value2 < 1 || value2 > 13)
			throw new IllegalArgumentException("Illegal playing card value");
		value = value2;
		suit = suit2;
		path = path2;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}
	
	public String getPath() {
		return "./Images/" + path + ".png";
	}

	public String getSuitasChar() {
		switch (suit) {
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUBS:
			return "Clubs";
		case SPADES:
			return "Spades";
		default:
			return "Joker";
		}

	}

	public String getValueAsString() {

		switch (value) {
		case 1:
			return "Ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		default:
			return "King";

		}

	}

	public String toString() {

		return getValueAsString() + " of " + getSuitasChar();

	}

}
