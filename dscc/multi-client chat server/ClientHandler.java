//6.	To develop a program for multi-client chat server

class ClientHandler implements Runnable {
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    // Constructor
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = true;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                // Receive the string
                received = dis.readUTF();
                System.out.println(received);

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.s.close();
                    break;
                }

                // Break the string into message and recipient parts
                String[] tokens = received.split("#");
                String msgToSend = tokens[0];
                String recipient = tokens[1];

                // Search for the recipient in the connected devices list.
                // ar is the vector storing clients of active users
                for (ClientHandler mc : MultiChatServer.ar) {
                    // If the recipient is found, write on its
                    // output stream
                    if (mc.name.equals(recipient) && mc.isloggedin) {
                        mc.dos.writeUTF(this.name + ": " + msgToSend);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // Closing resources
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



// Individual Client Handling:
// Each connected client is represented by an instance of the ClientHandler class.
// It manages the input and output streams for communication with its associated client.

// Message Processing:
// The ClientHandler continuously reads messages from its client.
// If the received message is a logout signal, it marks the client as not logged in and closes resources.
// Otherwise, it extracts the recipient's name and broadcasts the message to the intended recipient.