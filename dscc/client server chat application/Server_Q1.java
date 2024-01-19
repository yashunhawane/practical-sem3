//Implement a simple client server chat application using Socket API

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Q1 {
    private ServerSocket server = null;
    private Socket socket = null;
    private DataInputStream in = null;

    public Server_Q1(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started, waiting for clients...");
            socket = server.accept();
            System.out.println("Client connected");
            in = new DataInputStream(socket.getInputStream());

            String line = "";
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println("Client: " + line);
                } catch (IOException i) {
                    System.out.println("Error reading message: " + i.getMessage());
                }
            }

            System.out.println("Closing connection");
            socket.close();
            in.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        Server_Q1 server = new Server_Q1(5000);
    }
}




// Constructor: The Server_Q1 constructor initializes the server by creating a socket and waiting for a client to connect. Once connected, it sets up input streams to receive messages from the client.

// Exception Handling: It handles IOException exceptions that may occur during socket creation or communication.

// Reading Messages: It enters a loop to continuously read messages from the client until the client sends "Over."

// Closing Resources: It closes the socket and in resources when the communication is over.

// Main Method: The main method creates an instance of Server_Q1 and starts the server.