//Using Socket API to Implement Java Client to Communicate with NIST Time of the Day Service
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class hellosocketprogramming_q1 {
    public static void main(String args[]) throws IOException {
        // Create a socket connection to the NIST time server on port 13
        Socket s = new Socket("time.nist.gov", 13);

        // Create a Scanner to read from the input stream of the socket
        Scanner ins = new Scanner(s.getInputStream());

        // Read and print lines from the server's response
        while (ins.hasNextLine()) {
            System.out.println(ins.nextLine());
        }

        // Close the socket (not shown in your code, but it's good practice to close resources)
        s.close();
    }
}


// Imports:
// java.io.IOException: This import is for handling input/output exceptions.
// java.net.Socket: This import is for creating socket connections.
// java.util.Scanner: This import is for reading input.

// Class Declaration:
// You declare a class named hellosocketprogramming_q1.

// Main Method:
// The program's execution begins in the main method.
// It accepts an array of strings (args) as parameters.
// It declares that it may throw an IOException, indicating that it's prepared to handle input/output errors.

// Socket Connection:
// The program creates a new Socket object to establish a connection with the NIST time server.
// The connection is made to the server at the host "time.nist.gov" on port 13. Port 13 is commonly used for the daytime protocol.

// Data Retrieval and Output:
// A Scanner is created to read data from the input stream of the socket.
// A while loop is used to iterate through the lines of data received from the server.
// Each line of data is printed to the console.

// Closing the Socket:
// Although not explicitly shown in your code, it's essential to close the Socket to release system resources after using it.