import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplExample extends UnicastRemoteObject implements Hello {
    public ImplExample() throws RemoteException {
        super();
    }

    @Override
    public List<Student> getStudents() throws RemoteException {
        List<Student> list = new ArrayList<>();
        String jdbcUrl = "jdbc:mysql://localhost:3306/details";
        String username = "root";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student_data")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String branch = rs.getString("branch");
                int percent = rs.getInt("percentage");
                String email = rs.getString("email");

                Student student = new Student();
                student.setID(id);
                student.setName(name);
                student.setBranch(branch);
                student.setPercent(percent);
                student.setEmail(email);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Database error", e);
        }

        return list;
    }
}
