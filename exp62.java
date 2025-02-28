import java.sql.*;
import java.util.Scanner;

public class exp62 {
    static final String URL = "jdbc:mysql://localhost:3306/store";
    static final String USER = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            int choice;

            do {
                System.out.println("\n1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product\n5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addProduct(con, sc);
                        break;
                    case 2:
                        viewProducts(con);
                        break;
                    case 3:
                        updateProduct(con, sc);
                        break;
                    case 4:
                        deleteProduct(con, sc);
                        break;
                }
            } while (choice != 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product Name: ");
        String name = sc.next();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String query = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setDouble(2, price);
        pstmt.setInt(3, qty);
        pstmt.executeUpdate();
    }

    public static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + " | " +
                               rs.getString("ProductName") + " | " +
                               rs.getDouble("Price") + " | " +
                               rs.getInt("Quantity"));
        }
    }

    public static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to Update: ");
        int id = sc.nextInt();
        System.out.print("Enter New Price: ");
        double price = sc.nextDouble();

        String query = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setDouble(1, price);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    public static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Product ID to Delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
