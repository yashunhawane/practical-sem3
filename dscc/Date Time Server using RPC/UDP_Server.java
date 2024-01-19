//8.	To implement a Date Time Server using RPC concept

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDP_Server {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(1234);
            System.out.println("The chat server is listening on port 1234.");

            DateFormat forDate = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat forTime = new SimpleDateFormat("hh:mm:ss");
            byte[] buf;

            while (true) {
                buf = new byte[65535];
                DatagramPacket DpReceive = new DatagramPacket(buf, buf.length);
                ds.receive(DpReceive);

                String inp = new String(buf, 0, DpReceive.getLength()).trim();
                if (inp.equals("BYE")) {
                    System.out.println("Client is saying Bye... exiting");
                    break;
                }

                Date date = new Date();
                String toReturn = "";

                if (inp.equals("Date"))
                    toReturn = forDate.format(date);
                else if (inp.equals("Time"))
                    toReturn = forTime.format(date);

                System.out.println("Sending result ...");
                buf = toReturn.getBytes();
                InetAddress clientAddress = DpReceive.getAddress();
                int clientPort = DpReceive.getPort();
                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, clientAddress, clientPort);
                ds.send(DpSend);
            }

            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



// UDP_Server:

//     Socket Initialization:
//         Creates a DatagramSocket on port 1234 to listen for client requests.

//     Date and Time Formatting:
//         Sets up DateFormatters for date and time.

//     Communication Loop:
//         Enters into a loop to continuously listen for incoming packets.

//     Data Reception:
//         Receives a request from the client.
//         Processes the request ("Date" or "Time") and prepares a response.

//     Data Transmission:
//         Sends the response back to the client using a DatagramPacket.

//     Exit Condition:
//         Breaks out of the loop if the client sends "BYE."

//     Closing the Socket:
//         Closes the DatagramSocket when the server is done listening.