//EchoChatHandler
//Making the Echo Chat Server Multi-Threaded to Support Multiple Simultaneous Chatting Sessions



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class EchoChatHandler implements Runnable {
    private final Socket userSocket;
    private int userId;

    public EchoChatHandler(Socket userSocket, int userId) {
        this.userSocket = userSocket;
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            InputStream inS = userSocket.getInputStream();
            OutputStream outS = userSocket.getOutputStream();
            Scanner in = new Scanner(inS);
            PrintWriter out = new PrintWriter(outS, true);

            out.println("Hello user " + userId + "! I am the chat server. Let's Chat. Say BYE to disconnect");
            boolean bye = false;

            while (!bye && in.hasNextLine()) {
                String cMsg = in.nextLine();
                out.println("Echo: " + cMsg);

                if (cMsg.trim().equals("BYE")) {
                    bye = true;
                }
            }

            userSocket.close();
        } catch (IOException ioEX) {
            ioEX.printStackTrace();
        }
    }
}



// The MultiUserEchoChatServer creates a ServerSocket and listens for incoming connections. For each connection, it creates an EchoChatHandler instance and starts it in a new thread.

// The EchoChatHandler class implements the Runnable interface, allowing it to be executed in a separate thread. It handles communication with a single user.

// Each user thread reads input from its respective user and echoes it back. If the user sends "BYE," the thread terminates, and the user socket is closed.

// Note that there was a small correction in the EchoChatHandler constructor where the parameter userCnt should be named userId.