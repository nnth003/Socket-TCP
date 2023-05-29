//package TCP;
//
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.BorderLayout;
//import javax.swing.border.TitledBorder;
//import javax.swing.border.EtchedBorder;
//import java.awt.Color;
//import java.awt.GridLayout;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.Socket;
//
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.SwingConstants;
//import javax.swing.UIManager;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class ClientChatter extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField txtStaff;
//	private JTextField txtServerIP;
//	private JTextField txtServerPort;
//	
//	Socket mngSocket = null ;
//	String mngIP = "";
//	int mngPort = 0 ;
//	String staffName = "";
//	BufferedReader bf = null ;
//	DataOutputStream os = null ;
//	OutputThread t = null ;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClientChatter frame = new ClientChatter();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//					frame.setTitle("CLIENT");
//					
//
//					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	public ClientChatter() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 601, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout(0, 0));
//		
//		JPanel panel = new JPanel();
//		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
//		contentPane.add(panel, BorderLayout.NORTH);
//		panel.setLayout(new GridLayout(1, 7, 0, 0));
//		
//		JLabel lblNewLabel = new JLabel("Name: ");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel.add(lblNewLabel);
//		
//		txtStaff = new JTextField();
//		panel.add(txtStaff);
//		txtStaff.setColumns(10);
//		
//		JLabel lblNewLabel_1 = new JLabel("IP: ");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel.add(lblNewLabel_1);
//		
//		txtServerIP = new JTextField();
//		txtServerIP.setText("localhost");
//		panel.add(txtServerIP);
//		txtServerIP.setColumns(10);
//		
//		JLabel lblNewLabel_2 = new JLabel("Port: ");
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel.add(lblNewLabel_2);
//		
//		txtServerPort = new JTextField();
//		panel.add(txtServerPort);
//		txtServerPort.setColumns(10);
//		
//		JFrame thisJframe = this ;
//		
//		JButton btnConnect = new JButton("Connect");
//		btnConnect.setFocusable(false);
//		btnConnect.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				mngIP = txtServerIP.getText();
//				mngPort= Integer.parseInt(txtServerPort.getText());
//				staffName = txtStaff.getText()+" ";
//				try {
//					mngSocket = new Socket(mngIP, mngPort);
//					if(mngSocket != null ) {
//						ChatPanel p = new ChatPanel(mngSocket,staffName ,"Manager");
//						thisJframe.getContentPane().add(p);
//						p.getTxtMessages().append("Manager is running");
//						p.updateUI();
//						
//						bf = new BufferedReader(new InputStreamReader(mngSocket.getInputStream()));
//						os = new  DataOutputStream(mngSocket.getOutputStream());
//						
//						os.writeBytes("staff:"+staffName);
//						os.write(13); os.write(10);
//						os.flush();
//						
//					}
//				} catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//				}
//			}
//		});
//		panel.add(btnConnect);
//
//	}
//
//}

package TCP;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientChatter extends JFrame {

	private JPanel contentPane;
	private JTextField txtStaff;
	private JTextField txtServerIP;
	private JTextField txtServerPort;
	
	Socket mngSocket = null ;
	String mngIP = "";
	int mngPort = 0 ;
	String staffName = "";
	BufferedReader bf = null ;
	DataOutputStream os = null ;
	OutputThread t = null ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LoginForm form = new LoginForm();
//					form.setVisible(true);
					ClientChatter frame = new ClientChatter();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setTitle("CLIENT");
					

					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientChatter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 7, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Name: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel);
		
		txtStaff = new JTextField();
		panel.add(txtStaff);
		txtStaff.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("IP: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_1);
		
		txtServerIP = new JTextField();
		txtServerIP.setText("localhost");
		panel.add(txtServerIP);
		txtServerIP.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Port: ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_2);
		
		txtServerPort = new JTextField();
		panel.add(txtServerPort);
		txtServerPort.setColumns(10);
		
		JFrame thisJframe = this ;
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setFocusable(false);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mngIP = txtServerIP.getText();
				mngPort= Integer.parseInt(txtServerPort.getText());
				staffName = txtStaff.getText()+" ";
				try {
					mngSocket = new Socket(mngIP, mngPort);
					if(mngSocket != null ) {
						ChatPanel p = new ChatPanel(mngSocket,staffName ,"Manager");
						thisJframe.getContentPane().add(p);
						p.getTxtMessages().append("Manager is running");
						p.updateUI();
						
						bf = new BufferedReader(new InputStreamReader(mngSocket.getInputStream()));
						os = new  DataOutputStream(mngSocket.getOutputStream());
						
						os.writeBytes("staff:"+staffName);
						os.write(13); os.write(10);
						os.flush();
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		panel.add(btnConnect);

	}

}

