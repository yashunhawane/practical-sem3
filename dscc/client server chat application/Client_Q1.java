//Implement a simple client server chat application using Socket API

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_Q1 {
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream output = null;

    public Client_Q1(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to the server");
            input = new BufferedReader(new InputStreamReader(System.in));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println("Host not found: " + u.getMessage());
        } catch (IOException i) {
            System.out.println("Error establishing connection: " + i.getMessage());
        }

        String line = "";
        while (!line.equals("Over")) {
            try {
                System.out.print("Client: ");
                line = input.readLine();
                output.writeUTF(line);
            } catch (IOException i) {
                System.out.println("Error sending message: " + i.getMessage());
            }
        }

        try {
            input.close();
            output.close();
            socket.close();
            System.out.println("Connection closed");
        } catch (IOException i) {
            System.out.println("Error closing resources: " + i.getMessage());
        }
    }

    public static void main(String args[]) {
        Client_Q1 client = new Client_Q1("127.0.0.1", 5000);
    }
}



// Constructor: The Client_Q1 constructor initializes the client by creating a socket, setting up input and output streams. It then enters a loop where it reads input from the user and sends it to the server until the user enters "Over."

// Exception Handling: It handles UnknownHostException and IOException exceptions that may occur during socket creation.

// Closing Resources: The input, output, and socket resources are closed in a finally block to ensure proper resource management.

// Main Method: The main method creates an instance of Client_Q1 and connects it to the server.