import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class Server {
    public static void main(String args[]) {
        try {
            ImplExample obj = new ImplExample();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", obj);
            System.err.println("Server ready");
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

