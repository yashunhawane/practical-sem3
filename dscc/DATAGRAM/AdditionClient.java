// Using Datagram Socket API to Implement Number Addition Service

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class AdditionClient {
    public static void main(String[] args) {
        try {
            InetAddress addServiceIP = InetAddress.getLocalHost();
            int addServicePort = 8189;
            DatagramSocket clientSocket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the list of numbers: ");
            String numberList = sc.nextLine();
            DatagramPacket outDP = new DatagramPacket(numberList.getBytes(), numberList.length(), addServiceIP, addServicePort);
            clientSocket.send(outDP);
            System.out.println("Adding the numbers " + numberList + " together");
            byte[] buffer = new byte[256];
            DatagramPacket inDP = new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(inDP);
            String servResp = new String(inDP.getData(), 0, inDP.getLength());
            System.out.println(servResp);
            String stop = "STOP";
            outDP = new DatagramPacket(stop.getBytes(), stop.length(), addServiceIP, addServicePort);
            clientSocket.send(outDP);
            clientSocket.close();
        } catch (IOException ioEX) {
            System.out.println(ioEX);
        }
    }
}



// he client creates a DatagramSocket to establish communication.
// It takes input from the user, which is a list of numbers.
// The list of numbers is converted to bytes and sent as a DatagramPacket to the server at the specified IP address and port.
// The client then waits to receive a response from the server.
// Once the response is received, it is printed to the console.
// The client sends a "STOP" message to the server to indicate that it has finished sending numbers.
// The client closes the DatagramSocket.