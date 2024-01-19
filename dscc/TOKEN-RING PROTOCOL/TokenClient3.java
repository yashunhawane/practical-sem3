//Using Datagram Socket API to Implement Token Ring Protocol to Enforce Distributed Mutual Exclusion

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class TokenClient3 {
    private static DatagramSocket ds;
    private static DatagramPacket dp;
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        boolean hasToken = false;

        try {
            ds = new DatagramSocket(300);
        } catch (SocketException ex) {
            ex.printStackTrace();
            throw ex;
        }

        while (true) {
            if (hasToken) {
                System.out.println("Do you want to say something? (Type Y for Yes, N for No)");
                br = new BufferedReader(new InputStreamReader(System.in));
                String userResp = br.readLine();

                if (userResp.equalsIgnoreCase("Y")) {
                    System.out.println("Enter what you want to send:");
                    String userData = "Client 3 ===> " + br.readLine();
                    System.out.println("Getting ready to send data ...");
                    byte buff[] = userData.getBytes();
                    System.out.println("Sending...");
                    ds.send(new DatagramPacket(buff, buff.length, InetAddress.getLocalHost(), 1000));
                    System.out.println("Data Sent.");
                } else {
                    System.out.println("Since I am in busy state... passing token to client 1.");
                    String tokenMsg = "Token";
                    byte[] bf1 = new byte[1024];
                    bf1 = tokenMsg.getBytes();
                    ds.send(new DatagramPacket(bf1, bf1.length, InetAddress.getLocalHost(), 100));
                    hasToken = false;
                }
            } else {
                System.out.println("Entering the receiving mode ...");
                byte bf[] = new byte[1024];
                ds.receive(dp = new DatagramPacket(bf, bf.length));
                String msgClient2 = new String(dp.getData(), 0, dp.getLength());
                System.out.println("The data received from left neighbor (client 2) is: " + msgClient2);
                if (msgClient2.equalsIgnoreCase("Token")) {
                    hasToken = true;
                }
            }
        }
    }
}




// TokenClient2 and TokenClient3 follow a similar structure to TokenClient1.
// Each client creates a DatagramSocket on a specific port (200 for Client 2, 300 for Client 3) for receiving messages.
// Clients check if they have the token and take actions accordingly:
//     If they have the token, they prompt the user to enter a message. If the user responds with "Y," they send the message to the server. Otherwise, they pass the token to the next client.
//     If they don't have the token, they receive a message from the left neighbor and print it.