package MultiUserChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 1234);
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Ask for client name
            System.out.print("Enter your name: ");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String clientName = userInput.readLine();
            out.println(clientName);

            Thread receivingThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread sendingThread = new Thread(() -> {
                try {
                    String message;
                    while (true) {
                        message = userInput.readLine();
                        out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receivingThread.start();
            sendingThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
