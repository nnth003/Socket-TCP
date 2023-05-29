package ChatSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 8642);
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String string="", string2="";
		while (!string.equals("stop")) {
			string = reader.readLine();
			dataOutputStream.writeUTF(string);
			dataOutputStream.flush();
			string2 = dataInputStream.readUTF();
			System.out.println("Server say: "+string2);
		}
		dataOutputStream.close();
		socket.close();
	}
}
