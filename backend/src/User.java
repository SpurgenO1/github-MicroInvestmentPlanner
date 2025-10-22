import java.sql.*;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;

    // Register user
    public static boolean register(String name, String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Users (name,email,password) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password); // later hash
            return stmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login user
    public static User login(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                User u = new User();
                u.userId = rs.getInt("user_id");
                u.name = rs.getString("name");
                u.email = rs.getString("email");
                u.role = rs.getString("role");
                return u;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
