import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> hand;

	public Hand() {
		hand = new ArrayList<>();
	}

	public int getSize() {
		return hand.size();
	}

	public void emptyHand() {
		hand.clear();
	}

	public void addCard(Card c) {
		hand.add(c);

	}

	public void removeCard(Card c) {
		hand.remove(c);

	}

	public Card getCard(int position) {
		return hand.get(position);
	}

	public int getAces() {
		int counter = 0;
		for (int i = 0; i <= hand.size() - 1; i++) {
			if (getCard(i).getValue() == 1) {
				counter += 1;
			}
		}
		return counter;
	}

	public int getTotalValue() {
		int totalValue = 0;
		for (int i = 0; i <= getSize() - 1; i++) {
			if (getCard(i).getValue() == 1) {
				totalValue += 11;
			} else if (getCard(i).getValue() <= 10 && getCard(i).getValue() >= 2) {
				totalValue += getCard(i).getValue();
			} else {
				totalValue += 10;
			}
		}
		if (totalValue > 21) {
			int counter = getAces();

			while (totalValue > 21) {
				if (counter > 0) {
					totalValue -= 10;
					counter -= 1;
				} else
					return 0;
			}
			return totalValue;

		} else
			return totalValue;
	}

	public void sortBySuit() {

		ArrayList<Card> newHand = new ArrayList<>();
		while (hand.size() > 0) {
			int pos = 0; // Position of minimal card.
			Card c = hand.get(0); // Minimal card.
			for (int i = 1; i < hand.size(); i++) {
				Card c1 = hand.get(i);
				if (c1.getSuit() < c.getSuit() || (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue())) {
					pos = i;
					c = c1;
				}
			}
			hand.remove(pos);
			newHand.add(c);
		}
		hand = newHand;
	}

	public void sortByValue() {

		ArrayList<Card> newHand = new ArrayList<>();
		while (hand.size() > 0) {
			int pos = 0; // Position of minimal card.
			Card c = hand.get(0); // Minimal card.
			for (int i = 1; i < hand.size(); i++) {
				Card c1 = hand.get(i);
				if (c1.getValue() < c.getValue() || (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit())) {
					pos = i;
					c = c1;
				}
			}
			hand.remove(pos);
			newHand.add(c);
		}
		hand = newHand;
	}

	public void sort() {
		MergeSort sort = new MergeSort(hand);
		sort.sortGivenArray();

	}

	public void addat(int pos, Card c) {
		hand.add(pos, c);
	}

}