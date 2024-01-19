//6.	To develop a program for multi-client chat server

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiChatServer {
    // Vector to store active clients
    static Vector<ClientHandler> ar = new Vector<>();
    // Counter for clients
    static int i = 0;

    public static void main(String[] args) {
        try {
            // Server is listening on port 1234
            ServerSocket ss = new ServerSocket(1234);

            // Running an infinite loop for getting client requests
            while (true) {
                // Accept the incoming request
                Socket s = ss.accept();
                System.out.println("New client request received: " + s);

                // Obtain input and output streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Creating a new handler for this client...");

                // Create a new handler object for handling this request
                ClientHandler mtch = new ClientHandler(s, "client " + i, dis, dos);

                // Create a new Thread with this object
                Thread t = new Thread(mtch);
                System.out.println("Adding this client to active client list");

                // Add this client to active clients
                ar.add(mtch);

                // Start the thread
                t.start();

                // Increment i for a new client.
                // i is used for naming only and can be replaced
                // by any naming scheme
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




// Server Setup:
// The server creates a ServerSocket and listens on a specific port (in this case, port 1234).
// It waits for incoming client connections in an infinite loop.

// Client Handling:
// For each incoming client connection, the server creates a ClientHandler thread to handle communication with that specific client.
// ClientHandler is responsible for reading messages from its client and broadcasting them to other clients.

// Client Vector:
// The server maintains a vector (ar) to keep track of active clients, represented by instances of the ClientHandler class.
// When a new client connects, a new ClientHandler is created, and the client is added to the vector.

// Message Broadcasting:
// When a client sends a message, the server breaks down the message into the actual message content and the recipient's name.
// The server then searches for the recipient in the list of active clients and sends the message to the intended recipient.

// Client Logout Handling:
// If a client sends a special message like "logout," the server marks the corresponding ClientHandler as not logged in and closes the associated resources.