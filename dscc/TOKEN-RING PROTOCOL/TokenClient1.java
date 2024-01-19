//Using Datagram Socket API to Implement Token Ring Protocol to Enforce Distributed Mutual Exclusion

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class TokenClient1 {
    private static DatagramSocket ds;
    private static DatagramPacket dp;
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        boolean hasToken = true;

        try {
            ds = new DatagramSocket(100);
        } catch (SocketException ex) {
            ex.printStackTrace();
            throw ex;
        }

        while (true) {
            if (hasToken) {
                System.out.println("Do you want to say Something (i.e., Send Data) to Server?: Type Y for Yes/N for No");
                br = new BufferedReader(new InputStreamReader(System.in));
                String userResp = br.readLine();

                if (userResp.equalsIgnoreCase("Y")) {
                    System.out.println("Enter what you want to send:");
                    String userData = "Client 1 ===> " + br.readLine();
                    System.out.println("Getting ready to send data ...");
                    byte buff[] = userData.getBytes();
                    System.out.println("Sending...");
                    ds.send(new DatagramPacket(buff, buff.length, InetAddress.getLocalHost(), 1000));
                    System.out.println("Data Sent.");
                } else {
                    System.out.println("Since I am in busy state... passing token to client 2.");
                    String tokenMsg = "Token";
                    byte[] bf1 = new byte[1024];
                    bf1 = tokenMsg.getBytes();
                    ds.send(new DatagramPacket(bf1, bf1.length, InetAddress.getLocalHost(), 200));
                    hasToken = false;
                }
            } else {
                System.out.println("Entering the receiving mode ...");
                byte bf[] = new byte[1024];
                ds.receive(dp = new DatagramPacket(bf, bf.length));
                String msgClient3 = new String(dp.getData(), 0, dp.getLength());
                System.out.println("The data received from left neighbor (client 3) is: " + msgClient3);
                if (msgClient3.equalsIgnoreCase("Token")) {
                    hasToken = true;
                }
            }
        }
    }
}




// TokenClient1 represents the first client in the token-based chat system.
// It creates a DatagramSocket on port 100 for receiving messages.
// The client has a token initially (hasToken is set to true).
// In a loop, the client checks if it has the token.
//     If it has the token, it prompts the user to enter a message. If the user responds with "Y," it sends the message to the server. Otherwise, it passes the token to the next client (Client 2).
//     If it doesn't have the token, it receives a message from the left neighbor (Client 3) and prints it.