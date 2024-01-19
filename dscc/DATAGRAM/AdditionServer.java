//Using Datagram Socket API to Implement Number Addition Service

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class AdditionServer {
    private int port = 8189;

    public static void main(String[] args) {
        AdditionServer addService = new AdditionServer();
        addService.start();
    }

    public void start() {
        try {
            DatagramSocket serverConn = new DatagramSocket(port);
            byte[] buffer = new byte[256];
            DatagramPacket inDP = new DatagramPacket(buffer, buffer.length);
            String clientReq, serverResp;
            
            do {
                serverConn.receive(inDP);
                InetAddress clientAddress = inDP.getAddress();
                int clientPort = inDP.getPort();
                clientReq = new String(inDP.getData(), 0, inDP.getLength());

                if (clientReq != null && !clientReq.trim().equals("STOP")) {
                    double sumResult = 0;
                    StringTokenizer st = new StringTokenizer(clientReq);
                    
                    try {
                        while (st.hasMoreTokens()) {
                            Double d = new Double(st.nextToken());
                            sumResult += d.doubleValue();
                        }
                        serverResp = "The result is " + sumResult;
                    } catch (NumberFormatException nEx) {
                        serverResp = "Sorry, your list contains an invalid number";
                    }
                    
                    DatagramPacket outDP = new DatagramPacket(serverResp.getBytes(), serverResp.length(),
                            clientAddress, clientPort);
                    serverConn.send(outDP);
                }
            } while (!clientReq.trim().equals("STOP"));
            
            serverConn.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }
}




// The server creates a DatagramSocket to listen for incoming packets on a specified port.
// It enters a loop where it continuously receives packets from clients.
// For each packet received, the server extracts the list of numbers from the packet.
// It then calculates the sum of the numbers and prepares a response string.
// The response is sent back to the client as a DatagramPacket.
// The server checks if the client wants to stop (indicated by the "STOP" message).
// If the client does not want to stop, the server continues listening for more packets.
// If the client sends a "STOP" message, the server closes the DatagramSocket.