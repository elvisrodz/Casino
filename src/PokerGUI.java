import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;



@SuppressWarnings("serial")
public class PokerGUI extends JPanel {

	BasicStratergy strat = new BasicStratergy();
	JPanel topPanel = new JPanel();
	JPanel dcardPanel = new JPanel();
	JPanel pcardPanel = new JPanel();
	JTextPane winlosebox = new JTextPane();
	JButton dealbutton = new JButton();
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
	Poker game = new Poker();// this processes the blackjack maths
	private final JTextPane CurrentBet = new JTextPane();
	private final JButton changebetbutton = new JButton();
	private final JTextPane SeenBalance = new JTextPane();
	boolean[] cardstodiscard = new boolean[5];
	JLabel[] cards = new JLabel[5];
	Random gen = new Random();
	int rnd;
	int count;
	private final JButton discardbutton = new JButton();
	JButton playagainbutton = new JButton();

	public PokerGUI(double money, SQLEditing SQL2, boolean isGuest2) {
		rnd = (gen.nextInt(100) + 20);
		PlayerBalance = money;
		SQL = SQL2;
		isGuest = isGuest2;
		topPanel.setBackground(new Color(0, 122, 0));
		pcardPanel.setBackground(new Color(0, 122, 0));

		topPanel.setLayout(new FlowLayout());
		dealbutton.setText("  Deal");
		dealbutton.addActionListener(new dealbutton());
		playerlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));

		playerlabel.setForeground(Color.LIGHT_GRAY);
		playerlabel.setText(" Player: ");
		winlosebox.setEditable(false);
		winlosebox.setText(" ");
		winlosebox.setFont(new java.awt.Font("Helvetica Bold", 1, 20));

		topPanel.add(winlosebox);
		topPanel.add(dealbutton);

		SeenBalance.setEditable(false);
		topPanel.add(SeenBalance);
		SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
		pcardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		pcardPanel.add(playerlabel);
		setLayout(new GridLayout(0, 1, 0, 0));
		dcardPanel.setBackground(new Color(0, 122, 0));
		dealerlabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		dealerlabel.setForeground(Color.LIGHT_GRAY);
		playerlabel.setText("  Player:  ");
		dealerlabel.setText("  Dealer:  ");
		dcardPanel.add(dealerlabel);
		add(dcardPanel);
		add(topPanel);

		changebetbutton.setText("Change Bet");

		topPanel.add(changebetbutton);
		CurrentBet.setEditable(false);
		CurrentBet.setText("Your bet: ");
		CurrentBet.setFont(new Font("Dialog", Font.BOLD, 20));

		topPanel.add(CurrentBet);
		add(pcardPanel);
		CurrentBet.setText(Integer.toString(currentbet));
		discardbutton.addActionListener(new discard());
		discardbutton.setText("  Discard");

		topPanel.add(discardbutton);
		changebetbutton.addActionListener(new changebetbutton());
		playagainbutton.setText("  Play Again");
		playagainbutton.addActionListener(new playagainbutton());
		playagainbutton.setEnabled(false);
		topPanel.add(playagainbutton);

	}// end BlackjackGUI

	public void display() {
		JFrame myFrame = new JFrame("Poker");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		myFrame.setContentPane(this);
		myFrame.setPreferredSize(new Dimension(700, 550));
//		myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Display the window.
		myFrame.pack();
		myFrame.setVisible(true);
	}// end display

	class dealbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changebetbutton.setEnabled(false);
			;
			game.Shuffle();
			for (int i = 0; i < cardstodiscard.length; i++) {
				cardstodiscard[i] = false;
			}

			game.DealFirst(player);
			game.DealFirst(dealer);
			player.sort();
			dealer.sort();
			drawhands();

			playerlabel.setText(game.ValueToString(player)); //
			dealbutton.setEnabled(false);
			discardbutton.setEnabled(true);

			for (int i = 0; i < dealer.getSize(); i++) {
				ImageIcon tempimage = new ImageIcon(new ImageIcon("Images/card_back.png").getImage()
						.getScaledInstance(CARDW, CARDH, Image.SCALE_SMOOTH));
				dcardPanel.add(new JLabel(tempimage));
			}

			for (int i = 0; i < cards.length; i++) {
				cards[i].addMouseListener(new cardclicked(i));
			}

		}
	}

	class cardclicked implements MouseListener {
		int theno;

		public cardclicked(int i) {
			theno = i;

		}

		public void mouseClicked(MouseEvent e) {

			cardstodiscard[theno] = !cardstodiscard[theno];
			if (cardstodiscard[theno]) {
				cards[theno].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
			} else {
				cards[theno].setBorder(BorderFactory.createEmptyBorder());
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class discard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			discardbutton.setEnabled(false);
			discardhand(player);
			pcardPanel.removeAll();
			pcardPanel.repaint();
			pcardPanel.add(playerlabel);
			drawhands();
			dealerturn();
			playagainbutton.setEnabled(true);
		}
	}

	class playagainbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (PlayerBalance + currentbet < currentbet + 1) {
				JOptionPane.showMessageDialog(null, "Bet is above your total cash", "ERROR", JOptionPane.ERROR_MESSAGE,
						null);
				betchanger();

			}
			PlayerBalance -= currentbet;
			SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
			updateBalance();
			dealerlabel.setText("Dealer: ");
			playerlabel.setText("Player: ");
			winlosebox.setText("");

			dealer = new Hand();
			player = new Hand();

			dcardPanel.removeAll();
			pcardPanel.removeAll();
			dcardPanel.repaint();
			pcardPanel.repaint();
			playagainbutton.setEnabled(false);
			dealbutton.setEnabled(true);
			changebetbutton.setEnabled(true);

		}
	}// end playagainbutton

	class changebetbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			betchanger();
		}

	}

	public void drawhands() {

		playerlabel.setVisible(true);
		// playerlabel.setVisible(false);
		pcardPanel.add(playerlabel);

		for (int i = 0; i < cards.length; i++) {
			ImageIcon tempimage = new ImageIcon(new ImageIcon(player.getCard(i).getPath()).getImage()
					.getScaledInstance(CARDW, CARDH, Image.SCALE_SMOOTH));
			cards[i] = new JLabel(tempimage);
			pcardPanel.add(cards[i]);
		}
		playerlabel.setText(game.ValueToString(player)); // keep!!
		winlosebox.setText(" ");
	}

	public void updateBalance() {
		if (isGuest == false) {
			SQL.updateBalance(PlayerBalance);
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

	public void dealerturn() {
		for (int i = 0; i < cardstodiscard.length; i++) {
			cardstodiscard[i] = false;
		}
		game.playdealer(dealer, cardstodiscard);
		discardhand(dealer);

		dcardPanel.removeAll();
		dcardPanel.repaint();
		dcardPanel.add(dealerlabel);
		dealerlabel.setText(" " + dealerlabel.getText());
		for (int i = 0; i < dealer.getSize(); i++) {
			ImageIcon tempimage = new ImageIcon(new ImageIcon(dealer.getCard(i).getPath()).getImage()
					.getScaledInstance(CARDW, CARDH, Image.SCALE_SMOOTH));
			dcardPanel.add(new JLabel(tempimage));
		}

		dealerlabel.setText(game.ValueToString(dealer));
		comparehands();
	}

	public void discardhand(Hand thehand) {
		for (int i = 0; i < cardstodiscard.length; i++) {
			if (cardstodiscard[i] == true) {
				thehand.removeCard(thehand.getCard(i));
				thehand.addat(i, game.Hit());

			}
		}
		thehand.sort();
	}

	public void comparehands() {

		if (game.FindWinner(player, dealer) == "Player Wins") {
			PlayerBalance += currentbet * (game.getValue(player) + 1);
			SeenBalance.setText("Your Balance: " + Double.toString(PlayerBalance));
			updateBalance();
			winlosebox.setText(game.FindWinner(player, dealer) + " Payout x" + (game.getValue(player) + 1));
		} else {
			winlosebox.setText(game.FindWinner(player, dealer));
		}

		playagainbutton.setEnabled(true);

	}
}
