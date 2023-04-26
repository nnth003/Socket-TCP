import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public ChatClient(String serverAddress, int port) {
        try {
            // Kết nối đến server với địa chỉ và cổng cụ thể
            socket = new Socket(serverAddress, port);

            // Tạo luồng đọc dữ liệu từ server
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            inputReader = new BufferedReader(inputStreamReader);

            // Tạo luồng ghi dữ liệu lên server
            OutputStream outputStream = socket.getOutputStream();
            outputWriter = new PrintWriter(outputStream, true);

            // Khởi tạo luồng đọc dữ liệu từ server
            new Thread(new ServerListener()).start();
        } catch (IOException e) {
            System.out.println("Không thể kết nối đến server: " + e.getMessage());
        }
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message;

            // Đọc dữ liệu từ người dùng và gửi lên server
            while (true) {
                message = reader.readLine();
                if (message.equals("QUIT")) {
                    // Nếu người dùng nhập "QUIT" thì đóng kết nối và thoát
                    outputWriter.println(message);
                    break;
                }
                outputWriter.println(message);
            }

            // Đóng kết nối và đọc dữ liệu từ server
            inputReader.close();
            outputWriter.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc dữ liệu từ người dùng: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Sử dụng: java ChatClient <địa chỉ server> <cổng>");
            System.exit(1);
        }

        String serverAddress = args[0];
        int port = Integer.parseInt(args[1]);

        ChatClient chatClient = new ChatClient(serverAddress, port);
        chatClient.run();
    }

    // Luồng lắng nghe dữ liệu từ server và in ra màn hình
    private class ServerListener implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = inputReader.readLine()) != null) {
                    System.out.println("Nhận từ server: " + message);
                }
            } catch (IOException e) {
                System.out.println("Lỗi khi đọc dữ liệu từ server: " + e.getMessage());
            }
        }
    }
}