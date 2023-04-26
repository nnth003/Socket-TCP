//package Test2.copy;
//import java.io.*;
//import java.net.*;
//import java.util.*;
//public class ChatRoomClient {
//	 private static final String HOST = "localhost";
//	    private static final int PORT = 8080;
//
//	    public ChatRoomClient(Socket clientSocket, List<ChatRoomClient> clients) {
//			// TODO Auto-generated constructor stub
//		}
//
//		public static void main(String[] args) {
//	        try {
//	            Socket socket = new Socket(HOST, PORT);
//	            System.out.println("Connected to chat room server on " + HOST + ":" + PORT);
//
//	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//	            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//
//	            Thread receivingThread = new Thread(() -> {
//	                try {
//	                    String message;
//	                    while ((message = in.readLine()) != null) {
//	                        System.out.println(message);
//	                    }
//	                } catch (IOException e) {
//	                    System.err.println("Error receiving message: " + e.getMessage());
//	                    e.printStackTrace();
//	                }
//	            });
//
//	            receivingThread.start();
//
//	            String userInput;
//	            while ((userInput = stdIn.readLine()) != null) {
//	                out.println(userInput);
//	            }
//
//	            receivingThread.interrupt();
//	            socket.close();
//	        } catch (IOException e) {
//	            System.err.println("Error in chat room client: " + e.getMessage());
//	            e.printStackTrace();
//	        }
//	    }
//
//		}
