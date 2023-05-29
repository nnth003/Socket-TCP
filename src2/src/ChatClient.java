package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader userInput;

    public void start(String serverHost, int serverPort) {
        try {
            socket = new Socket(serverHost, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your username: ");
            String username = userInput.readLine();
            out.println(username);

            Thread inputThread = new Thread(new UserInputHandler());
            inputThread.start();

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class UserInputHandler implements Runnable {
    	@Override
        public void run() {
            try {
                String userInput;
                while ((userInput = ChatClient.this.userInput.readLine()) != null) {
                    out.println(userInput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start("localhost", 8888);
    }
}
