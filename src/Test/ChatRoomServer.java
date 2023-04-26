package Test;
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatRoomServer {
	 private static final int PORT = 9999;
	    private static Set<PrintWriter> clients = new HashSet<>();

	    public static void main(String[] args) throws Exception {
	        System.out.println("Server is running...");
	        ServerSocket serverSocket = new ServerSocket(PORT);

	        while (true) {
	            Socket clientSocket = serverSocket.accept();
	            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
	            clients.add(clientWriter);

	            ClientHandler clientHandler = new ClientHandler(clientSocket, clientWriter);
	            Thread clientThread = new Thread(clientHandler);
	            clientThread.start();
	        }
	    }

	    private static class ClientHandler implements Runnable {
	        private Socket clientSocket;
	        private PrintWriter clientWriter;
	        private BufferedReader clientReader;

	        public ClientHandler(Socket clientSocket, PrintWriter clientWriter) throws IOException {
	            this.clientSocket = clientSocket;
	            this.clientWriter = clientWriter;
	            this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        }

	        @Override
	        public void run() {
	            try {
	                String clientName = "Client " + clients.size();
	                String inputLine;

	                // Welcome message to new client
	                clientWriter.println("Welcome to the chat room, " + clientName + "!");
	                clientWriter.println("Type 'quit' to exit.");

	                // Broadcast message to all clients when a new client joins
	                broadcast(clientName + " has joined the chat room.");

	                // Read input from client and broadcast to all clients
	                while ((inputLine = clientReader.readLine()) != null) {
	                    if ("quit".equalsIgnoreCase(inputLine)) {
	                        break;
	                    }
	                    broadcast(clientName + ": " + inputLine);
	                }

	                // Close resources when client exits
	                clientWriter.println("You have left the chat room.");
	                broadcast(clientName + " has left the chat room.");
	                clients.remove(clientWriter);
	                clientWriter.close();
	                clientReader.close();
	                clientSocket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private static void broadcast(String message) {
	        for (PrintWriter writer : clients) {
	            writer.println(message);
	        }
	    }
}
