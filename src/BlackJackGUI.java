import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class BlackJackGUI extends JPanel {
	BasicStratergy strat = new BasicStratergy();
	JPanel topPanel = new JPanel();
	JPanel dcardPanel = new JPanel();
	JPanel pcardPanel = new JPanel();
	JTextPane winlosebox = new JTextPane();
	JButton hitbutton = new JButton();
	JButton dealbutton = new JButton();
	JButton staybutton = new JButton();
	JButton playagainbutton = new JButton();
	JLabel dealerlabel = new JLabel();
	JLabel playerlabel = new JLabel();

	/*************************************************************
	 * the labels to represent the cards for the game
	 *************************************************************/
	JLabel playercard1;
	JLabel playercard2;
	JLabel playercardhit;
	JLabel dealercard0;
	JLabel dealercard2;
	JLabel dealercard1;
	JLabel dealercardhit;

	int CARDW = 125;
	int CARDH = 200;
	int currentbet = 100;
	Double PlayerBalance;
	boolean doubled = false;
	int splitstate = 0;
	boolean isGuest;
	SQLEditing SQL;
	Hand dealer = new Hand(); // to hold the dealer's cards
	Hand player = new Hand(); // to hold the player's cards
	Hand splithand = new Hand();
	Blackjack game = new Blackjack();// this processes the blackjack maths
	private final JTextPane CurrentBet = new JTextPane();
	private final JButton changebetbutton = new JButton();
	private final JTextPane SeenBalance = new JTextPane();
	private final JButton doubledownbutton = new JButton();
	private final JTextPane stratbox = new JTextPane();
	private final JButton splitbutton = new JButton();
	private final JTextPane countbox = new JTextPane();

	Random gen = new Random();
	int rnd;
	int count;

	/*************************************************************
	 * Constructs the screen
	 *************************************************************/
	public BlackJackGUI(double money, SQLEditing SQL2, boolean isGuest2) {
		rnd = (gen.nextInt(100) + 20);
		PlayerBalance = money;
		SQL = SQL2;
		isGuest = isGuest2;
		topPanel.setBackground(new Color(0, 122, 0));
		pcardPanel.setBackground(new Color(0, 122, 0));
		
		topPanel.setLayout(new FlowLayout());
		dealbutton.setText("  Deal");
		dealbutton.addActionListener(new dealbutton());
		hitbutton.setText("  Hit");
		hitbutton.addActionListener(new hitbutton());
		hitbutton.setEnabled(false);
		staybutton.setText("  Stay");
		staybutton.addActionListener(new staybutton());
		staybutton.setEnabled(false);
		doubledownbutton.setEnabled(false);
		playagainbutton.setText("  Play Again");
		playagainbutton.addActionListener(new playagainbutton());
		playagainbutton.setEnabled(false);
		playerlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		playerlabel.setForeground(Color.LIGHT_GRAY);
		playerlabel.setText("  Player:  ");
		winlosebox.setEditable(false);
		winlosebox.setText(" ");
		winlosebox.setFont(new java.awt.Font("Helvetica Bold", 1, 20));
		splitbutton.setText("Split");
		splitbutton.addActionListener(new spiltbutton());
		splitbutton.setEnabled(false);
		topPanel.add(winlosebox);
		topPanel.add(dealbutton);
		topPanel.add(hitbutton);
		topPanel.add(staybutton);
		doubledownbutton.setText("Double Down");
		doubledownbutton.addActionListener(new doubledownbutton());
		topPanel.add(doubledownbutton);
		SeenBalance.setEditable(false);
		topPanel.add(SeenBalance);
		SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));

		topPanel.add(splitbutton);
		topPanel.add(playagainbutton);
		pcardPanel.add(playerlabel);
		setLayout(new GridLayout(0, 1, 0, 0));
		dcardPanel.setBackground(new Color(0, 122, 0));
		dealerlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		dealerlabel.setForeground(Color.LIGHT_GRAY);

		dealerlabel.setText("  Dealer:  ");
		dcardPanel.add(dealerlabel);
		add(dcardPanel);
		add(topPanel);
		changebetbutton.addActionListener(new changebetbutton());
		changebetbutton.setText("Change Bet");

		topPanel.add(changebetbutton);
		CurrentBet.setEditable(false);
		CurrentBet.setText("Your bet: ");
		CurrentBet.setFont(new Font("Dialog", Font.BOLD, 20));

		topPanel.add(CurrentBet);
		add(pcardPanel);
		CurrentBet.setText(Integer.toString(currentbet));
		stratbox.setText(" ");
		stratbox.setFont(new Font("Dialog", Font.BOLD, 20));
		stratbox.setEditable(false);
	
		
		topPanel.add(stratbox);
		countbox.setText(" ");
		countbox.setFont(new Font("Dialog", Font.BOLD, 20));
		countbox.setEditable(false);

		topPanel.add(countbox);
	}// end BlackjackGUI

	/*************************************************************
	 * Shows the screen
	 *************************************************************/
	public void display() {
		JFrame myFrame = new JFrame("BlackJack");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setContentPane(this);
		myFrame.setPreferredSize(new Dimension(700, 550));
		myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Display the window.
		myFrame.pack();
		myFrame.setVisible(true);
		//game = new Blackjack();
	}// end display

	/*************************************************************
	 * DealButton
	 * 
	 * @param e
	 *            Deal button pressed
	 *************************************************************/
	class dealbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			splitbutton.setEnabled(false);
			splitstate = 0;
			changebetbutton.setEnabled(false);
			doubledownbutton.setEnabled(true);
			dcardPanel.add(dealerlabel);
			pcardPanel.add(playerlabel);
			// pcardPanel.add(Balance);
			dealercard0 = new JLabel();
			dealercard1 = new JLabel();
			playercard1 = new JLabel();
			playercard2 = new JLabel();

			/**
			 * Get's dealer and player cards from Hand and the image associated
			 * with that random card and puts them on the screen.
			 */

			dealercard0 = new JLabel(new ImageIcon("back.jpg"));

			game.DealFirst(player, dealer);
			count += 2;
			// to iterate set and get current dealer cards

			ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/card_back.png").getImage()
					.getScaledInstance(CARDW, CARDH, Image.SCALE_SMOOTH));
			dealercard0.setIcon(imageIcon);

			imageIcon = new ImageIcon(new ImageIcon(dealer.getCard(0).getPath()).getImage().getScaledInstance(CARDW,
					CARDH, Image.SCALE_SMOOTH));
			dealercard1.setIcon(imageIcon);
			imageIcon = new ImageIcon(new ImageIcon(player.getCard(0).getPath()).getImage().getScaledInstance(CARDW,
					CARDH, Image.SCALE_SMOOTH));
			playercard1.setIcon(imageIcon);
			imageIcon = new ImageIcon(new ImageIcon(player.getCard(1).getPath()).getImage().getScaledInstance(CARDW,
					CARDH, Image.SCALE_SMOOTH));
			playercard2.setIcon(imageIcon);
			dcardPanel.add(dealercard1);
			dcardPanel.add(dealercard0);
			pcardPanel.add(playercard1);
			pcardPanel.add(playercard2);

			dealerlabel.setText("  Dealer:  " + dealer.getCard(0).getValueAsString());
			playerlabel.setText("  Player:  " + game.GetHand(player));

			hitbutton.setEnabled(true);
			staybutton.setEnabled(true);
			dealbutton.setEnabled(false);

			if (player.getCard(0).getValue() == player.getCard(1).getValue()) {
				splitbutton.setEnabled(true);
			}
			if (currentbet * 2 > PlayerBalance) {
				doubledownbutton.setEnabled(false);
			} else {
				doubledownbutton.setEnabled(true);
			}
			if (game.checkBlackJack(player)) {
				doubledownbutton.setEnabled(false);
				hitbutton.setEnabled(false);
				staybutton.setEnabled(true);
				dealbutton.setEnabled(false);
				playagainbutton.setEnabled(false);

				winlosebox.setText("BlackJack");
				PlayerBalance += currentbet * 1.5;
				SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
				updateBalance();
			} else {
				stratbox.setText(strat.gen(player.getCard(0).getValue(), player.getCard(1).getValue(),
						dealer.getCard(0).getValue(), player.getTotalValue()));
			}

			updatecount();
			// add(dcardPanel, BorderLayout.CENTER);
			// add(pcardPanel, BorderLayout.SOUTH);

		}
	}// end dealbutton

	/*************************************************************
	 * HitButton every time the player wants another card until hand value is
	 * over 21.
	 * 
	 * @param e
	 *            Hit button pressed
	 *************************************************************/
	class hitbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hit();
			if (game.Bust(player) && splitstate != 0) {
				winlosebox.setText("Bust");
				hitbutton.setEnabled(false);
				dealbutton.setEnabled(false);
			} else if (game.Bust(player)) {
				winlosebox.setText("Bust");
				hitbutton.setEnabled(false);
				dealbutton.setEnabled(false);
				staybutton.setEnabled(false);
				playagainbutton.setEnabled(true);
				game.CountUpdate(dealer.getCard(1).getValue());
				updatecount();
			} else {

				stratbox.setText(strat.update(dealer.getCard(0).getValue(), player.getTotalValue()));
			}
			doubledownbutton.setEnabled(false);
		}
	}// end hitbutton

	/*************************************************************
	 * StayButton dealer must hit on 17 or lower. determines the winner, player
	 * wins if under 21 and above dealer. Tie goes to dealer.
	 * 
	 * @param e
	 *            Stay button pressed
	 *************************************************************/
	class staybutton implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (splitstate == 1) {
				changehand();
				hitbutton.setEnabled(true);
				hit();

			} else if (splitstate == 2) {
				stay();
			} else {
				stay();
			}

		}// end staybutton

	}// end playagainbutton

	/*************************************************************
	 * PlayAgainButton resets screen
	 * 
	 * @param e
	 *            Play Again button pressed
	 *************************************************************/
	class playagainbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (splitstate == 2) {
				changehand();
				displaywinner();
				splitstate = 0;
			} else {
				if (doubled) {
					currentbet = currentbet / 2;
					CurrentBet.setText("Your Bet: " + Integer.toString(currentbet));
					doubled = false;
				}

				if (PlayerBalance + currentbet < currentbet + 1) {
					JOptionPane.showMessageDialog(null, "Bet is above your total cash", "ERROR",
							JOptionPane.ERROR_MESSAGE, null);
					betchanger();

				}
				PlayerBalance -= currentbet;
				SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
				updateBalance();
				dealerlabel.setText("Dealer: ");
				playerlabel.setText("Player: ");
				winlosebox.setText("");
				stratbox.setText("");
				dealer = new Hand();
				player = new Hand();

				dcardPanel.removeAll();
				pcardPanel.removeAll();
				dcardPanel.repaint();
				pcardPanel.repaint();
				hitbutton.setEnabled(false);
				staybutton.setEnabled(false);
				playagainbutton.setEnabled(false);
				dealbutton.setEnabled(true);
				changebetbutton.setEnabled(true);
				if (count >= rnd) {
					rnd = (gen.nextInt(100) + 20);
					count = 0;
					game.Shuffle();
					JOptionPane.showMessageDialog(null, "Deck has been Shuffled", null, JOptionPane.INFORMATION_MESSAGE, null);

					updatecount();
				}
			}
		}
	}// end playagainbutton

	class changebetbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			betchanger();
		}

	}

	class doubledownbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			doubled = true;
			int tempory = currentbet * 2;
			PlayerBalance -= currentbet;
			currentbet = tempory;
			SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
			updateBalance();
			doubledownbutton.setEnabled(false);
			hit();
			hitbutton.setEnabled(false);
			CurrentBet.setText(Integer.toString(tempory));

		}

	}

	class spiltbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
doubledownbutton.setEnabled(false);
			split();

		}
	}

	public void betchanger() {
		String temp;
		boolean bool;
		bool = true;
		do {
			temp = game.ChangeBet();

			if (temp == null) {

				bool = false;
			} else if (Integer.parseInt(temp) > PlayerBalance + currentbet || Integer.parseInt(temp) < 0) {
				JOptionPane.showMessageDialog(null, "Invalid bet", "ERROR", JOptionPane.ERROR_MESSAGE, null);

			} else {
				PlayerBalance += currentbet;
				currentbet = Integer.parseInt(temp);
				PlayerBalance -= Integer.parseInt(temp);
				SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
				updateBalance();
				CurrentBet.setText(temp);
				bool = false;

			}
		} while (bool);

	}

	public void hit() {

		player.addCard(game.Hit(player));
		count += 1;
		// player.sort();
		redrawhand();
		updatecount();
	}

	public void stay() {
		displaydealer();
		updatecount();
		displaywinner();

	}

	public void split() {
		splitbutton.setEnabled(false);
		PlayerBalance -= currentbet;
		SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
		updateBalance();
		splithand.emptyHand();
		splitstate = 1;
		splithand.addCard(player.getCard(1));
		player.removeCard(player.getCard(1));
		hit();
	}

	public void changehand() {
		Hand temp = new Hand();
		for (int i = 0; i < player.getSize(); i++) {
			temp.addCard(player.getCard(i));
		}

		player.emptyHand();

		for (int i = 0; i < splithand.getSize(); i++) {
			player.addCard(splithand.getCard(i));
		}

		splithand.emptyHand();

		for (int i = 0; i < temp.getSize(); i++) {
			splithand.addCard(temp.getCard(i));
		}
		splitstate = 2;
		redrawhand();
	}

	public void redrawhand() {
		pcardPanel.removeAll();
		pcardPanel.repaint();
		pcardPanel.add(playerlabel);
		for (int i = 0; i < player.getSize(); i++) {
			ImageIcon tempimage = new ImageIcon(new ImageIcon(player.getCard(i).getPath()).getImage()
					.getScaledInstance(CARDW, CARDH, Image.SCALE_SMOOTH));
			playercardhit = new JLabel(tempimage);
			pcardPanel.add(playercardhit);
		}

		playerlabel.setText("  Player:   " + game.GetHand(player));

	}

	public void displaywinner() {
		dealerlabel.setText("Dealer: " + game.GetHand(dealer));
		playerlabel.setText("Player: " + game.GetHand(player));
		if (game.FindWinner(player, dealer) == "Player wins") {
			PlayerBalance += currentbet * 2;
			SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
			updateBalance();
		}
		winlosebox.setText(game.FindWinner(player, dealer));
		hitbutton.setEnabled(false);
		staybutton.setEnabled(false);
		doubledownbutton.setEnabled(false);
		splitbutton.setEnabled(false);
		playagainbutton.setEnabled(true);
	}

	public void updatecount() {
		String temp;
		temp = game.getCount();
		countbox.setText("Current Count: " + temp);
		// write up count
	}

	public void displaydealer() {
		dcardPanel.remove(dealercard0);
		dcardPanel.add(dealercard1);

		dcardPanel.removeAll();
		dcardPanel.repaint();
		dcardPanel.add(dealerlabel);
		dealerlabel.setText(" " + dealerlabel.getText());
		game.DealerTurn(dealer);
		updatecount();
		// iterate through cards and re-display
		Card dhitcard = null;
		for (int i = 0; i <= dealer.getSize() - 1; i++) {
			{
				dhitcard = dealer.getCard(i);
				ImageIcon tempimage = new ImageIcon(new ImageIcon(dhitcard.getPath()).getImage()
						.getScaledInstance(CARDW, CARDH, Image.SCALE_SMOOTH));
				dealercardhit = new JLabel(tempimage);
				dcardPanel.add(dealercardhit);
			}
		}
	}

	public void updateBalance() {
		if (isGuest == false) {
			SQL.updateBalance(PlayerBalance);
		}
	}
}// end BlackjackGUI