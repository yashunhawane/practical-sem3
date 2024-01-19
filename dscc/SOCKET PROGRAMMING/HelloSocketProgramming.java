//Using TELNET Client to Communicate with NIST Time
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HelloSocketProgramming {
    public static void main(String args[]) {
        try (Socket s = new Socket("time.nist.gov", 13);
             Scanner ins = new Scanner(s.getInputStream())) {

            while (ins.hasNextLine()) {
                System.out.println(ins.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



// import java.io.IOException;: Import the IOException class for handling input/output exceptions.
// import java.net.Socket;: Import the Socket class for creating socket connections.
// import java.util.Scanner;: Import the Scanner class for reading input.

// Inside the main method:

// Socket s = new Socket("time.nist.gov", 13);: Create a new socket connection to the NIST time server on port 13 (which is commonly used for the daytime protocol). This line may throw an IOException, so you've declared it in the method signature using throws IOException.

// Scanner ins = new Scanner(s.getInputStream());: Create a Scanner to read from the input stream of the socket. This allows you to read data sent by the server.

// while (ins.hasNextLine()) { System.out.println(ins.nextLine()); };: Enter a loop that reads lines from the input stream using ins.nextLine() and prints them to the console using System.out.println(). The loop continues until there are no more lines to read