package Test;
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatRoomClient {
	  private static final String SERVER_HOST = "localhost";
	    private static final int SERVER_PORT = 9999;

	    public static void main(String[] args) throws Exception {
	        System.out.println("Welcome to the chat room!");
	        System.out.println("Type 'quit' to exit.");

	        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
	             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
	             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	             Scanner scanner = new Scanner(System.in)) {

	            String serverMessage;
	            String userInput;

	            // Read messages from server
	            while ((serverMessage = reader.readLine()) != null) {
	                System.out.println(serverMessage);
	            }

	            // Read input from user and send to server
	            while (!(userInput = scanner.nextLine()).equalsIgnoreCase("quit")) {
	                writer.println(userInput);
	            }

	            // Close resources when user exits
	            writer.close();
	            reader.close();
	            scanner.close();
	        }
	    }
}
