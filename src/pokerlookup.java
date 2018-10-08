
public class pokerlookup {
	String smallcardtype, largecardtype;
	Hand hand;
	String tempvalue;
	int sameCards = 1, sameCards2 = 1;
	int smallGroupRank = 0, largeGroupRank = 0;
	int[] ranks = new int[14];
	int highestvalue;

	public pokerlookup(Hand thehand) {
		hand = thehand;

	}

	public String returnvalues() {

		if (checkflush()) {
			if (checkstraight()) {
				return "Straight Flush";

			} else {
				return "Flush";
			}
		} else if (checkstraight()) {
			return "Straight";
		}
		checkmultiples();
		if (sameCards > 1) {
			if (sameCards2 == 1) {
				return Integer.toString(sameCards);
			} else if ((sameCards + sameCards2) == 4) {
				return "2 Pairs";
			} else if ((sameCards + sameCards2) == 5) {
				return "Full House";
			}
		}
		return "none";

	}

	public boolean checkflush() {
		for (int i = 0; i < hand.getSize() - 1; i++) {
			if (hand.getCard(i).getSuit() != hand.getCard(i + 1).getSuit()) {
				return false;
			}
		}
		return true;
	}

	public boolean checkstraight() {
		int lastvalue;
		lastvalue = hand.getCard(0).getValue();
		for (int i = 1; i < hand.getSize(); i++) {
			if ((lastvalue + 1) == hand.getCard(i).getValue()) {
				lastvalue = hand.getCard(i).getValue();
			} else {
				return false;
			}
		}

		return true;

	}

	public void checkmultiples() {
		sameCards = 1;
		sameCards2 = 1;
		for (int i = 0; i < ranks.length; i++) {
			ranks[i] = 0;
		}
		for (int i = 0; i < hand.getSize(); i++) {
			ranks[hand.getCard(i).getValue()] += 1;
		}

		// initialze to 1int largeGroupRank=0,smallGroupRank=0;
		smallGroupRank = 0;
		largeGroupRank = hand.getCard(4).getValue();
		for (int x = 13; x >= 1; x--) {
			if (ranks[x] > sameCards) {
				if (sameCards != 1)
				// if sameCards was not the default value
				{
					sameCards2 = sameCards;
					smallGroupRank = largeGroupRank;
				}

				sameCards = ranks[x];
				largeGroupRank = x;

			} else if (ranks[x] > sameCards2) {
				sameCards2 = ranks[x];
				smallGroupRank = x;
			}

		}
		for (int i = 0; i < hand.getSize(); i++) {
			if (hand.getCard(i).getValue() == smallGroupRank) {
				smallcardtype = hand.getCard(i).getValueAsString();
			} else if (hand.getCard(i).getValue() == largeGroupRank) {
				largecardtype = hand.getCard(i).getValueAsString();
			}

		}

	}

	public void dealerchecker(boolean[] cardstodiscard) {
		String result = returnvalues();
		if (result == "none") {
			for (int i = 0; i < cardstodiscard.length; i++) {
				cardstodiscard[i] = true;
			}
		} else if (result == "Full House" || result == "Straight" || result == "Straight Flush" || result == "Flush") {
			for (int i = 0; i < cardstodiscard.length; i++) {
				cardstodiscard[i] = false;
			}
		} else {
			for (int i = 0; i < ranks.length; i++) {
				if (ranks[i] < 2) {
					for (int j = 0; j < hand.getSize(); j++) {
						if (i == hand.getCard(j).getValue()) {
							cardstodiscard[j] = true;
						}
					}
				}
			}
		}
	}


	public String FindHighestCard() {
		if (checkflush()) {
			if (checkstraight()) {
				return hand.getCard(hand.getSize()-1).getValueAsString();

			} else {
				return hand.getCard(hand.getSize()-1).getValueAsString();
			}
		} else if (checkstraight()) {
			return hand.getCard(hand.getSize()-1).getValueAsString();
		}
		checkmultiples();
		return largecardtype;

	}
public int FindhighestValue(){
	if (checkflush()) {
		if (checkstraight()) {
			return hand.getCard(hand.getSize()-1).getValue();

		} else {
			return hand.getCard(hand.getSize()-1).getValue();
		}
	} else if (checkstraight()) {
		return hand.getCard(hand.getSize()-1).getValue();
	}
	checkmultiples();
	return largeGroupRank;
}

}
