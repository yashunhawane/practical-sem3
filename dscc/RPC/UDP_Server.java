//To implement a Server calculator using RPC concept. (Make use of datagram)

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(1234);

        while (true) {
            byte[] receiveData = new byte[65535];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(receivePacket);

            String input = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("Equation Received: " + input);

            if (input.equalsIgnoreCase("Exit")) {
                System.out.println("Server exiting.");
                break;
            }

            int result = evaluateEquation(input);
            String resultStr = Integer.toString(result);
            byte[] sendData = resultStr.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                    receivePacket.getAddress(), receivePacket.getPort());
            ds.send(sendPacket);
        }

        ds.close();
    }

    private static int evaluateEquation(String equation) {
        StringTokenizer st = new StringTokenizer(equation);
        int operand1 = Integer.parseInt(st.nextToken());
        String operator = st.nextToken();
        int operand2 = Integer.parseInt(st.nextToken());

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    System.out.println("Error: Division by zero");
                    return 0; // Handle division by zero error
                }
            default:
                System.out.println("Error: Invalid operator");
                return 0; // Handle invalid operator error
        }
    }
}



// Input Handling:

//     The server assumes that the input equation is always in the correct format. You might want to add more robust input parsing and error handling to handle unexpected input gracefully.

// Tokenization:

//     The server uses StringTokenizer, which is a bit outdated. Consider using String.split() or a more modern approach for tokenization.

// Division by Zero:

//     The server does not check for division by zero. You might want to add a check to handle this case and inform the client about the error.

// Closing Resources:

//     It's a good practice to close the DatagramSocket when the server is done running. Consider adding a ds.close() statement outside the loop to close the socket when the server is exiting..