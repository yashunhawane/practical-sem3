//7.	To implement a Server calculator using RPC concept. (Make use of datagram)

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        InetAddress serverIp = InetAddress.getLocalHost();

        while (true) {
            System.out.println("Enter the equation in the format:");
            System.out.println("'Operand1 Operator Operand2'");
            String input = sc.nextLine();

            byte[] sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIp, 1234);
            ds.send(sendPacket);

            if (input.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting client.");
                break;
            }

            byte[] receiveData = new byte[65535];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(receivePacket);

            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Answer: " + result);
        }

        sc.close();
        ds.close();
    }
}



// Client Code:

//     Input Validation:
//         The client doesn't perform any input validation on the equation. It assumes that the input is always in the correct format. You might want to add some validation checks to ensure the input is as expected.

//     InetAddress.getLocalHost():
//         Using InetAddress.getLocalHost() on the client side might not work as expected in some cases, especially if the client and server are on different machines. Consider using the actual IP address or hostname of the server.