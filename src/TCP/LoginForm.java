package TCP;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class LoginForm extends JFrame {
	public JTextField usernameField;
	public JPasswordField passwordField;
	public JButton loginButton;
	public JButton signButton;
	public ServerChatter managerChatter;

	public LoginForm() {
//		this.managerChatter = chatter;
		// Tạo giao diện đăng nhập
		setTitle("Đăng nhập");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 150);
		setLocationRelativeTo(null);

		// Tạo các thành phần trong form
		JPanel panel = new JPanel();
		JLabel usernameLabel = new JLabel("Tên đăng nhập:");
		JLabel passwordLabel = new JLabel("Mật khẩu:");
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Đăng nhập");
		loginButton.setFocusable(false);
		signButton = new JButton("Đăng Kí");
		signButton.setFocusable(false);

		// Đặt bố cục cho các thành phần
		panel.setLayout(new GridLayout(3, 2));
		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(signButton);
		panel.add(loginButton);
		

		// Đăng ký sự kiện cho nút đăng nhập
//        loginButton.addActionListener(this);
		this.login();

		// Hiển thị giao diện
		add(panel);
		setVisible(true);
//		managerChatter = new ManagerChatter();
//		managerChatter.setVisible(true);
		
	}

	public void login() {
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (username.equals("") || password.equals("")) {
					JOptionPane.showConfirmDialog(rootPane, "Dữ liệu trống", "Error", 1);
				} else if (checkSignIn(username, password) == true) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công.", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
//					ManagerChatter managerChatter = new ManagerChatter();
//					managerChatter.setVisible(true);
					ClientChatter clientChatter = new ClientChatter();
					clientChatter.setVisible(true);
					clientChatter.setTitle("Name Account: " + username);
					clientChatter.setLocationRelativeTo(null);
				} else {
					JOptionPane.showMessageDialog(null, "Đăng nhập không thành công. Vui lòng thử lại.", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});
		signButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (username.equals("") || password.equals("")) {
					JOptionPane.showConfirmDialog(rootPane, "Dữ liệu trống", "Error", 1);
				} else if (checkSignIn(username, password) == true) {
					JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại! Vui lòng đăng kí mới");
				} else {

					try {
						PreparedStatement pst = null;
						Connection conn = null;

						Connection connect = JDBC.getConnection();
						Statement st;
						st = connect.createStatement();
						String sql = "INSERT INTO userr(username, pass) VALUES ('" + username + "','" + password + "')";
						System.out.println(sql);
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Đăng Ký Tài Khoản Thành Công!");
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Loi");
					}

				}

			}
		});
//		managerChatter = new ManagerChatter();
//		String username = loginForm.usernameField.getText();
//		String password = String.valueOf(loginForm.passwordField.getPassword());
//
//		if (username.equals("") || password.equals("")) {
//			JOptionPane.showConfirmDialog(rootPane, "Some Fields Are is Empty", "Error", 1);
//		} else if (loginForm.checkSignIn(username, password) == true) {
//			JOptionPane.showMessageDialog(null, "Đăng nhập thành công.", "Thông báo",
//					JOptionPane.INFORMATION_MESSAGE);
//			loginForm.setVisible(false);
////			ManagerChatter managerChatter = new ManagerChatter();
////			managerChatter.setVisible(true);
//			ClientChatter clientChatter = new ClientChatter();
//			clientChatter.setVisible(true);
//			clientChatter.setLocationRelativeTo(null);
//		} else {3
//			JOptionPane.showMessageDialog(null, "Đăng nhập không thành công. Vui lòng thử lại.", "Thông báo",
//					JOptionPane.INFORMATION_MESSAGE);
//		}
	}

	public boolean checkSignIn(String username, String password) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = JDBC.getConnection();
			String sql = "SELECT * FROM userr WHERE username = ? AND pass = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();

			return rs.next(); // Trả về true nếu dữ liệu đúng, ngược lại trả về false
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
//            JDBC.close(rs);
//            JDBC.close(pst);
			JDBC.close(conn);
		}
	}

//	public static void main(String[] args) {
//
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			new LoginForm();
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}