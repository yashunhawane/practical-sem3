import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementing the remote interface
public class ImplExample implements Hello {
    // Implementing the interface method
    public List<Student> getStudents() throws Exception {
        List<Student> list = new ArrayList<>();

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/details1";
        String USER = "root";
        String PASS = "admin";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM bill")) {

            while (rs.next()) {
                int id = rs.getInt("bill_amount");
                String name = rs.getString("consumer_name");
                String branch = rs.getString("bill_due_date");
                // Uncomment the following lines if you have these properties in your Student class
                // int percent = rs.getInt("percentage");
                // String email = rs.getString("email");

                Student student = new Student();
                student.setID(id);
                student.setName(name);
                student.setBranch(branch);
                // student.setPercent(percent);
                // student.setEmail(email);
                list.add(student);
            }
        }

        return list;
    }
}
