import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Hello stub = (Hello) registry.lookup("Hello");
            List<Student> list = stub.getStudents();

            for (Student s : list) {
                System.out.println("ID: " + s.getId());
                System.out.println("Name: " + s.getName());
                System.out.println("Branch: " + s.getBranch());
                System.out.println("Percentage: " + s.getPercent());
                System.out.println("Email: " + s.getEmail());
                System.out.println("------------");
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
