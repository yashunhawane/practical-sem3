//Implementing a Simple Echo Chat Server Using Java Server Socket API


import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
    public static void main(String args[]) throws IOException {
        // Create a server socket on port 8189
        ServerSocket serverSocket = new ServerSocket(8189);
        System.out.println("I am about to listen on 8189");

        // Accept a client connection
        Socket clientSocket = serverSocket.accept();

        // Get input and output streams from the client socket
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        // Create a Scanner to read from the input stream and a PrintWriter to write to the output stream
        Scanner scanner = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream, true);

        // Send an initial greeting message to the client
        out.println("Hello!.. I am the chat server. Let's Chat. Say BYE to disconnect");

        boolean bye = false;

        // Continue reading input from the client and echoing back until "BYE" is received
        while (!bye && scanner.hasNextLine()) {
            String clientMessage = scanner.nextLine();

            // Echo back the client's message with "Echo: " prefix
            out.println("Echo: " + clientMessage);

            // Check if the client wants to disconnect
            if (clientMessage.trim().equals("BYE")) {
                bye = true;
            }
        }

        // Close the client socket and server socket
        clientSocket.close();
        serverSocket.close();
    }
}


// Explanation:

//     The server socket is created on port 8189 using ServerSocket.

//     The server listens for incoming connections and accepts a client connection when one is made (serverSocket.accept()).

//     Input and output streams are obtained from the client socket to read and write data.

//     A greeting message is sent to the client using out.println().

//     The server enters a loop where it reads lines from the client using scanner.nextLine() and echoes them back with a prefix "Echo: ". If the client sends "BYE", the loop terminates.

//     Finally, the client socket and server socket are closed.