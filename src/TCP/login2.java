package TCP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login2 extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public login2() {
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

        // Đặt bố cục cho các thành phần
        panel.setLayout(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        // Đăng ký sự kiện cho nút đăng nhập
        loginButton.addActionListener(this);

        // Hiển thị giao diện
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Kiểm tra thông tin đăng nhập
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập không thành công. Vui lòng thử lại.");
        }
    }

    public static void main(String[] args) {
        new login2();
    }
}