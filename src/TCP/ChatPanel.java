package TCP;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ChatPanel extends JPanel {

	Socket socket = null ;
	BufferedReader bf = null ;
	DataOutputStream os = null ;
	OutputThread t = null ;
	String sender ; 
	String receiver ;
	JTextArea txtMessages;

	
	
	public ChatPanel(Socket s , String sender , String receiver) {
		setLayout(new BorderLayout(0,0));
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Message",TitledBorder.LEADING, TitledBorder.TOP, null, null ));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2,0,0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JTextArea txtMessage = new JTextArea();
		scrollPane.setViewportView(txtMessage);
		
		JButton btnsend = new JButton("Send");
		btnsend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtMessage.getText().trim().length() == 0) return ;
				try {
					os.writeBytes(txtMessage.getText());
					os.write(13);os.write(10);
					os.flush();
					txtMessages.append("\n"+sender+txtMessage.getText());
					txtMessage.setText("");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		panel.add(btnsend);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, BorderLayout.CENTER);
		
		txtMessages = new JTextArea();
		scrollPane_1.setViewportView(txtMessages);
		
		this.socket = s ;
		this.sender = sender ;
		this.receiver = receiver ;
		try {
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new DataOutputStream(socket.getOutputStream());
			t = new OutputThread(s, txtMessages, sender, receiver);
			t.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public JTextArea getTxtMessages() {
		return this.txtMessages;
		
	}

}
//setBounds(100, 100, 450, 385);
//contentPane = new JPanel();
//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//
//contentPane.setLayout(new BorderLayout(0, 0));
//
//JPanel panelmessenger = new JPanel();
//panelmessenger.setBorder(new TitledBorder(null, "Messenger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//contentPane.add(panelmessenger, BorderLayout.SOUTH);
//panelmessenger.setLayout(new GridLayout(1, 0, 2, 0));