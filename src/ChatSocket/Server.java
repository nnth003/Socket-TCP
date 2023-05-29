package ChatSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		int SERVER_PORT = 7;
		ServerSocket serverSocket = new ServerSocket(8642);
		
//		System.out.println(serverSocket);
		Socket socket = serverSocket.accept();
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String string = "", string2 = "";
		while (!string.equals("stop")) {
			string = dataInputStream.readUTF();
			System.out.println("Client Says: "+string);
			string2 = reader.readLine();
			dataOutputStream.writeUTF(string2);
			dataOutputStream.flush();
		}
		dataInputStream.close();
		serverSocket.close();
		socket.close();
	}
}
