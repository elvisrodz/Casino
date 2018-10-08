
public class BasicStratergy {

	// split[i][j] = should you split with (i, i) if dealer is showing j
	String[][] split = {
			// A 2 3 4 5 6 7 8 9 10
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", },
			{ " ", "H", "P", "P", "P", "P", "P", "P", "H", "H", "H" }, // 2
			{ " ", "H", "P", "P", "P", "P", "P", "P", "H", "H", "H" }, // 3
			{ " ", "H", "H", "H", "H", "P", "P", "H", "H", "H", "H" }, // 4
			{ " ", "H", "D", "D", "D", "D", "D", "D", "D", "D", "H" }, // 5
			{ " ", "H", "P", "P", "P", "P", "P", "H", "H", "H", "H" }, // 6
			{ " ", "H", "P", "P", "P", "P", "P", "P", "H", "H", "H" }, // 7
			{ " ", "P", "P", "P", "P", "P", "P", "P", "P", "P", "P" }, // 8
			{ " ", "S", "P", "P", "P", "P", "P", "S", "P", "P", "S" }, // 9
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" }, // 10
			{ " ", "P", "P", "P", "P", "P", "P", "P", "P", "P", "P" },// Ace
	};

	// soft[i][j] = should you hit with (A, i) if dealer is showing j
	String[][] soft = {
			// A 2, 3, 4, 5, 6, 7, 8, 9, 10
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", },
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H" }, // 13
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H" }, // 14
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H" }, // 15
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H" }, // 16
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H" }, // 17
			{ " ", "H", "S", "D", "D", "D", "D", "S", "S", "H", "H" }, // 18
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" }, // 19
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" }, // 20
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" }, // 21

	};

	// soft[i][j] = should you hit with total = i if dealer is showing j
	String[][] hard = {
			// A 2 3 4 5 6 7 8 9 10
			{ " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", },
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", }, // 5.1
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", }, // 6.2
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", }, // 7.3
			{ " ", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", }, // 8.4
			{ " ", "H", "H", "D", "D", "D", "D", "H", "H", "H", "H", }, // 9.5
			{ " ", "H", "D", "D", "D", "D", "D", "D", "D", "D", "H", }, // 10.6
			{ " ", "H", "D", "D", "D", "D", "D", "D", "D", "D", "D", }, // 11.7
			{ " ", "H", "H", "S", "S", "S", "H", "H", "H", "H", "H", }, // 12.8
			{ " ", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", }, // 13.9
			{ " ", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", }, // 14.10
			{ " ", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", }, // 15.11
			{ " ", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", }, // 16.12
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", }, // 17.13
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", }, // 18.14
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", }, // 19.15
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", }, // 20.16
			{ " ", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", }, // 21.17
	};

	// debug this

	public String gen(int x, int y, int z, int total) {

		total = total - 4;

		// if y is an ace, flip cards
		if (y == 1) {
			y = x;
			x = 1;
		}

		// split
		if (x == y) {
			if (x > 10) {
				x = 10;
			}
			if (y > 10) {
				y = 10;
			}
			if (z > 10) {
				z = 10;
			}
			
			switch (split[x][z]) {
			case "P":
				return "Split";
			case "S":
				return "Stay";
			case "D":
				return "Split then Double Down";
			default:
				return "Hit";
			}

		}
		if (x > 10) {
			x = 10;
		}
		if (y > 10) {
			y = 10;
		}
		if (z > 10) {
			z = 10;
		}
		// a single ace
		if (x == 1) {
			switch (soft[y][z]) {
			case "D":
				return "Double Down";
			case "S":
				return "Stay";
			default:
				return "Hit";
			}

		}

		// no ace and did not split.0

		else {

			switch (hard[total][z]) {
			case "D":
				return "Double Down";
			case "S":
				return "Stay";

			default:
				return "Hit";
			}

		}
	}
 public String update(int z, int total) {
		total = total - 4;


		if (z > 10) {
			z = 10;
		}
		switch (hard[total][z]) {
		case "D":
			return "Double Down";
		case "S":
			return "Stay";

		default:
			return "Hit";
		}

	}
 }

