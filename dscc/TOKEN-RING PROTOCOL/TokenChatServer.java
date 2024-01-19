//Using Datagram Socket API to Implement Token Ring Protocol to Enforce Distributed Mutual Exclusion
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TokenChatServer {
    private static DatagramSocket ds;
    private static DatagramPacket dp;

    public static void main(String[] args) throws IOException {
        try {
            ds = new DatagramSocket(1000);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }

        while (true) {
            byte buff[] = new byte[1024];
            dp = new DatagramPacket(buff, buff.length);
            ds.receive(dp);
            String str = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Message From: " + str);
        }
    }
}


// he TokenChatServer class creates a DatagramSocket on port 1000 to listen for incoming messages.
// It enters an infinite loop where it continuously receives DatagramPackets containing messages from clients.
// The received message is printed to the console, indicating the source of the message.