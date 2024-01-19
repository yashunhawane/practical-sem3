//8.	To implement a Date Time Server using RPC concept

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Client {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getLocalHost();
            byte[] buf;
            
            while (true) {
                System.out.println("What do you want to know? Date / Time");
                String inp = sc.nextLine();
                
                buf = inp.getBytes();
                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);
                ds.send(DpSend);
                
                if (inp.equals("BYE"))
                    break;
                
                buf = new byte[65535];
                DatagramPacket DpReceive = new DatagramPacket(buf, buf.length);
                ds.receive(DpReceive);
                System.out.println(inp + ": " + new String(buf, 0, DpReceive.getLength()));
            }

            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




// UDP_Client:

//     Socket Initialization:
//         Creates a DatagramSocket to enable communication with the server.

//     User Input:
//         Uses a Scanner to get user input for the desired information (Date/Time).
//         Converts the input string to bytes and sends it to the server using a DatagramPacket.

//     Communication Loop:
//         Enters into a loop to continuously send and receive data from the server.
//         If the user enters "BYE," it breaks out of the loop.

//     Data Reception:
//         Receives the server's response using a DatagramPacket.
//         Prints the received information.

//     Closing the Socket:
//         Closes the DatagramSocket when the communication is complete.