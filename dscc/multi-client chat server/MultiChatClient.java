//To develop a program for multi-client chat server
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiChatClient {
    final static int ServerPort = 1234;

    public static void main(String args[]) {
        try {
            Scanner scn = new Scanner(System.in);

            // Getting localhost IP
            InetAddress ip = InetAddress.getByName("localhost");

            // Establish the connection
            Socket s = new Socket(ip, ServerPort);

            // Obtaining input and output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // sendMessage thread
            Thread sendMessage = new Thread(() -> {
                while (true) {
                    // Read the message to deliver.
                    String msg = scn.nextLine();
                    try {
                        dos.writeUTF(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            // readMessage thread
            Thread readMessage = new Thread(() -> {
                while (true) {
                    try {
                        // Read the message sent to this client
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            sendMessage.start();
            readMessage.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




// Connection Establishment:
// The client creates a Socket to establish a connection with the server.
// It uses DataInputStream and DataOutputStream to handle input and output streams for communication.

// Message Threads:
// The client has two threads: sendMessage and readMessage.
// sendMessage is responsible for reading messages from the user and sending them to the server.
// readMessage continuously reads messages from the server and prints them to the client's console.

// User Interaction:
// The user can input messages via the console, and these messages are sent to the server for broadcasting to other clients.
// Received messages from the server are displayed on the client's console.