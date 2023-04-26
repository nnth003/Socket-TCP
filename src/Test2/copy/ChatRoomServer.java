//package Test2.copy;
//import java.io.*;
//import java.net.*;
//import java.util.*;
//public class ChatRoomServer {
//	private static final int PORT = 8080;
//    private static List<ChatRoomClient> clients = new ArrayList<>();
//
//    public static void main(String[] args) {
//        try {
//            ServerSocket serverSocket = new ServerSocket(PORT);
//            System.out.println("Chat room server is running on port " + PORT);
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("A new client has joined the chat room: " + clientSocket);
//
//                ChatRoomClient clientHandler = new ChatRoomClient(clientSocket, clients);
//                clients.add(clientHandler);
//                clientHandler).start();
//            }
//        } catch (IOException e) {
//            System.err.println("Error in chat room server: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
