import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

public class SQLEditing {
	String username;
	Connection conn;

	public SQLEditing(String user) {
		username = user;

		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL database connection
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/casino?" + "&useSSL=false&" + "user=root&password=Password12");

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {

		}

	}

	public double getMoney() {
		double balance = 0;
		try {

			PreparedStatement pst = conn.prepareStatement(
					"select balance from `profile`, `accounts` where `profile`.`name` = `accounts`.`username` and `profile`.`name` =? ;");
			pst.setString(1, username);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				balance = rs.getDouble("balance");
			}
			return balance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void newUser(String hash) {
		try {

			PreparedStatement pst = conn
					.prepareStatement("INSERT INTO `casino`.`accounts` (`username`, `password`) VALUES (?, ?)");
			pst.setString(1, username);
			pst.setString(2, hash);
			pst.executeUpdate();
			pst.close();
			pst = conn.prepareStatement("INSERT INTO `casino`.`profile` (`name`, `balance`) VALUES (?, '1000')");
			pst.setString(1, username);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	boolean validate_login(String password) {
		try {

			PreparedStatement pst = conn.prepareStatement(
					"Select `username` FROM accounts WHERE `username`=?");
			pst.setString(1, username);
			//pst.setString(2, password);
			String temphash = null;
			ResultSet rs = pst.executeQuery();
			// String
			
			if (rs.next()) {
				pst.close();
				pst = conn.prepareStatement("Select `password` FROM accounts WHERE `username`=?");
				pst.setString(1, username);
				rs = pst.executeQuery();
				while (rs.next()) {
					temphash = rs.getString("password");
				}
				return BCrypt.checkpw(password, temphash);
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean validateusername() {
		try {

			PreparedStatement pst = conn
					.prepareStatement("Select `username`, `password` FROM accounts WHERE `username`=? ");
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();

			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void updateBalance(Double money) {

		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE `casino`.`profile` SET `balance`=? WHERE `name`=?");
			pst.setDouble(1, money);
			pst.setString(2, username);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
