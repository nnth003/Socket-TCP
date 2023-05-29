package TCP;


import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;


public class ServerChatter extends JFrame implements Runnable{

	private JPanel contentPane;
	private JTextField txtServerPort;
	ServerSocket srvSocket = null ;
	BufferedReader bf = null ;
	Thread t ;
	private JTabbedPane tabbedPane;
	public JButton newClientButton;
	public LoginForm loginForm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerChatter frame = new ServerChatter();
					frame.setVisible(true);
					frame.setTitle("SERVER");
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ServerChatter() {
//		this.loginForm = new LoginForm();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 3, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Port: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);
		
		txtServerPort = new JTextField();
		txtServerPort.setText("8888");
		panel.add(txtServerPort);
		txtServerPort.setColumns(5);
		
		newClientButton = new JButton("New Client");
		panel.add(newClientButton);
		newClientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginForm loginForm = new LoginForm();
//				loginForm.setTitle();
			}
		});
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		this.setSize(600, 300);
		int serverPort = Integer.parseInt(txtServerPort.getText());
		try {
			srvSocket = new ServerSocket(serverPort);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Socket aStaffSocket = srvSocket.accept();
				if(aStaffSocket != null) {
					bf= new BufferedReader(new InputStreamReader(aStaffSocket.getInputStream()));
					String S = bf.readLine();
					int pos = S.indexOf(":") ;
					String staffName = S.substring(pos+1);
					ChatPanel  p = new ChatPanel(aStaffSocket, "SERVER: ", staffName) ;
					tabbedPane.add(staffName, p);
					p.updateUI();
				
				}
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
}
