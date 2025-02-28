import java.sql.*;

public class exp61 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM Employee";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("EmpID | Name | Salary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + " | " +
                                   rs.getString("Name") + " | " +
                                   rs.getDouble("Salary"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
