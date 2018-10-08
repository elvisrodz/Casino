
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.mindrot.jbcrypt.BCrypt;

@SuppressWarnings("serial")
public class Login extends javax.swing.JFrame {
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
	private JButton btnLoginAsGuest;
	double money = 1000;
	SQLEditing SQL;
	

	public Login() {
		initComponents();
	}

	private void initComponents() {

		jPasswordField1 = new javax.swing.JPasswordField();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));

		jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
		jLabel2.setText("Password");

		jButton1.setText("Login");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LoginButtonActionPerformed(evt);
			}
		});
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			}
		});

		jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
		jLabel1.setText("UserName");

		jLabel3.setBackground(new java.awt.Color(128, 128, 128));
		jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
		jLabel3.setText("Login to the System");

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ExitbuttonActionPerformed(evt);
			}
		});

		btnLoginAsGuest = new JButton();
		btnLoginAsGuest.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GuestButtonActionPerformed(evt);
			}

		});
		
		btnLoginAsGuest.setText("Login As Guest");

		JButton btnRegisterUser = new JButton();
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RegButtonActionPerformed(evt);
			}
		});
		btnRegisterUser.setText("Register User");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
				.addGap(22)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE).addGap(33)
						.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addGap(22).addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 232,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(119, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap(111, Short.MAX_VALUE)
						.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE).addGap(62))
				.addGroup(layout.createSequentialGroup().addContainerGap(159, Short.MAX_VALUE).addGroup(layout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRegisterUser, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLoginAsGuest, GroupLayout.PREFERRED_SIZE, 136,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addComponent(jButton1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton))))
						.addGap(180)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap().addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(18).addComponent(jLabel2,
								GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(19).addComponent(jPasswordField1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnLoginAsGuest)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING).addComponent(jButton1).addComponent(btnNewButton))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnRegisterUser).addGap(6)));
		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] { jButton1, btnNewButton });
		getContentPane().setLayout(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void LoginButtonActionPerformed(ActionEvent evt) {

		if (jTextField1.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
		} else if (jPasswordField1.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
		} else {
			String user = jTextField1.getText();
			char[] pass = jPasswordField1.getPassword();
			String pwd = String.copyValueOf(pass);

			// hash/salt pwd

			SQL = new SQLEditing(user);
			if ((SQL.validate_login(pwd))) {
				JOptionPane.showMessageDialog(null, "Correct Login Credentials");
				money = SQL.getMoney();

		//	main.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");
			}

		}
	}

	private void ExitbuttonActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void GuestButtonActionPerformed(ActionEvent evt) {

	//	main = new Menu(money, SQL, true, "Guest");
		//main.setVisible(true);
		this.setVisible(false);
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


}
