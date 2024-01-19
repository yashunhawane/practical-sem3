import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Creating Remote interface for our application
public interface Hello extends Remote {
    List<Student> getStudents() throws RemoteException;
}

