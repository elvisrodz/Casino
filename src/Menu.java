import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.mindrot.jbcrypt.BCrypt;

public class Menu extends JFrame {
	private JPasswordField jPasswordField1;
	private JTextField jTextField1;
	private JTextPane Title;
	double money = 1000;
	private SQLEditing SQL;
	private JPanel mainmenu = new JPanel();
	private JPanel login = new JPanel();
	private String user;
	private boolean isGuest;
	
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		setSize(400, 300);

		setLocationRelativeTo(null);

		setTitle("Main Menu");
		getContentPane().setLayout(new CardLayout(0, 0));

		login.setBackground(new Color(0, 128, 0));
		getContentPane().add(login, "name_5812603638569");
		login.setLayout(null);

		JLabel jLabel2 = new JLabel();
		jLabel2.setText("Password");
		jLabel2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		jLabel2.setBounds(18, 112, 66, 20);
		login.add(jLabel2);

		jPasswordField1 = new JPasswordField();
		jPasswordField1.setBounds(99, 113, 235, 24);
		login.add(jPasswordField1);

		JLabel jLabel1 = new JLabel();
		jLabel1.setText("UserName");
		jLabel1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		jLabel1.setBounds(18, 77, 78, 20);
		login.add(jLabel1);

		jTextField1 = new JTextField();
		jTextField1.setBounds(100, 77, 232, 29);
		login.add(jTextField1);

		JLabel jLabel3 = new JLabel();
		jLabel3.setText("Login to the System");
		jLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		jLabel3.setBackground(Color.GRAY);
		jLabel3.setBounds(66, 15, 302, 48);
		login.add(jLabel3);

		JButton btnRegisterUser = new JButton();
		btnRegisterUser.setText("Register User");
		btnRegisterUser.setBounds(149, 224, 136, 23);
		login.add(btnRegisterUser);
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RegButtonActionPerformed(evt);
			}
		});

		JButton btnLoginAsGuest = new JButton();
		btnLoginAsGuest.setText("Login As Guest");
		btnLoginAsGuest.setBounds(149, 148, 136, 23);
		login.add(btnLoginAsGuest);
		btnLoginAsGuest.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GuestButtonActionPerformed(evt);
			}

		});

		JButton jButton1 = new JButton();
		jButton1.setText("Login");
		jButton1.setBounds(150, 185, 65, 23);
		login.add(jButton1);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LoginButtonActionPerformed(evt);
			}
		});

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(215, 185, 68, 23);
		login.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ExitbuttonActionPerformed(evt);
			}
		});

		mainmenu.setBackground(new Color(0, 122, 0));
		getContentPane().add(mainmenu, "name_5805456790512");
		SpringLayout sl_mainmenu = new SpringLayout();
		mainmenu.setLayout(sl_mainmenu);

		Title = new JTextPane()  {
					@Override public void setBorder(Border border) {
						
				}
			 
			    };
		sl_mainmenu.putConstraint(SpringLayout.NORTH, Title, 32, SpringLayout.NORTH, mainmenu);
		sl_mainmenu.putConstraint(SpringLayout.WEST, Title, 5, SpringLayout.WEST, mainmenu);
		sl_mainmenu.putConstraint(SpringLayout.EAST, Title, 379, SpringLayout.WEST, mainmenu);
		Title.setEditable(false);
		Title.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		Title.setBackground(new Color(0, 122, 0));
		mainmenu.add(Title);
		
	     SimpleAttributeSet attribs = new SimpleAttributeSet();  
	     StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);  
	     Title.setParagraphAttributes(attribs,true);
		
		
		JButton btnBlackJack = new JButton("BlackJack");
		sl_mainmenu.putConstraint(SpringLayout.WEST, btnBlackJack, 154, SpringLayout.WEST, mainmenu);
		mainmenu.add(btnBlackJack);
		btnBlackJack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BlackJackbtnClicked(evt);
			}
		});
		JButton btnPoker = new JButton("Poker");
		sl_mainmenu.putConstraint(SpringLayout.WEST, btnPoker, 163, SpringLayout.WEST, mainmenu);
		sl_mainmenu.putConstraint(SpringLayout.SOUTH, btnPoker, -88, SpringLayout.SOUTH, mainmenu);
		sl_mainmenu.putConstraint(SpringLayout.SOUTH, btnBlackJack, -32, SpringLayout.NORTH, btnPoker);
		mainmenu.add(btnPoker);
		btnPoker.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PokerbtnClicked(evt);
			}
		});
		JButton btnLogout = new JButton("Logout");
		sl_mainmenu.putConstraint(SpringLayout.NORTH, btnLogout, 32, SpringLayout.SOUTH, btnPoker);
		sl_mainmenu.putConstraint(SpringLayout.WEST, btnLogout, 160, SpringLayout.WEST, mainmenu);
		mainmenu.add(btnLogout);
		btnLogout.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LogoutbtnClicked(evt);
			}
		});
	}

	private void LoginButtonActionPerformed(ActionEvent evt) {

		if (jTextField1.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
		} else if (jPasswordField1.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
		} else {
			 user = jTextField1.getText();
			char[] pass = jPasswordField1.getPassword();
			String pwd = String.copyValueOf(pass);

			// hash/salt pwd

			SQL = new SQLEditing(user);
			if ((SQL.validate_login(pwd))) {
				JOptionPane.showMessageDialog(null, "Correct Login Credentials");
				money = SQL.getMoney();
				isGuest = false;
				openmenu();

			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");
			}

		}
	}

	private void ExitbuttonActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void GuestButtonActionPerformed(ActionEvent evt) {
		isGuest = true;
		openmenu();
	}

	private void RegButtonActionPerformed(ActionEvent evt) {
		if (jTextField1.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
		} else if (jPasswordField1.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
		} else {
			String user = jTextField1.getText();
			char[] pass = jPasswordField1.getPassword();
			String pwd = String.copyValueOf(pass);
			// hash/salt pwd
			String salt = BCrypt.gensalt();
			String hash = BCrypt.hashpw(pwd, salt);
			SQL = new SQLEditing(user);
			if ((SQL.validateusername()) == false) {
				SQL.newUser(hash);
				JOptionPane.showMessageDialog(null, "User Registered please Login");
			} else {
				JOptionPane.showMessageDialog(null, "User is already taken");
			}

		}
	}

	private void openmenu() {
		mainmenu.setVisible(true);
		login.setVisible(false);
		if (isGuest == false) {
			Title.setText("Hello "+ user + " Your Balance is "+ money);
		} else {
			Title.setText("Hello Guest Your Balance is "+ money);
		}
		
	}
	private void BlackJackbtnClicked(ActionEvent evt) {
		BlackJackGUI blackjack = new BlackJackGUI(money, SQL, isGuest);
		blackjack.display();
		

	}
	private void PokerbtnClicked(ActionEvent evt) {
		PokerGUI poker = new PokerGUI(money, SQL, isGuest);
		poker.display();
	
		
	}
	private void LogoutbtnClicked(ActionEvent evt) {
		mainmenu.setVisible(false);
		login.setVisible(true);
	}

}
