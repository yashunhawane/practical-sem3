//MultiUserEchoChatServer
//Making the Echo Chat Server Multi-Threaded to Support Multiple Simultaneous Chatting Sessions


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiUserEchoChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8189);
        System.out.println("I am about to listen on 8189");
        int userCnt = 0;

        while (true) {
            Socket userSocket = server.accept();
            userCnt++;

            // Create a new EchoChatHandler for the connected user and start it in a separate thread
            EchoChatHandler echoChatter = new EchoChatHandler(userSocket, userCnt);
            Thread userChatThread = new Thread(echoChatter);

            System.out.println("Spawning a new chatting thread for user " + userCnt);
            userChatThread.start();
        }
    }
}




// The MultiUserEchoChatServer creates a ServerSocket and listens for incoming connections. For each connection, it creates an EchoChatHandler instance and starts it in a new thread.

// The EchoChatHandler class implements the Runnable interface, allowing it to be executed in a separate thread. It handles communication with a single user.

// Each user thread reads input from its respective user and echoes it back. If the user sends "BYE," the thread terminates, and the user socket is closed.

// Note that there was a small correction in the EchoChatHandler constructor where the parameter userCnt should be named userId