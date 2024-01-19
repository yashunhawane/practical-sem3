import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String args[]) {
        try {
            ImplExample obj = new ImplExample();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Create RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
