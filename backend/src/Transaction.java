import java.sql.*;
import java.util.ArrayList;

public class Transaction {
    public int transactionId;
    public int investmentId;
    public String type;
    public double amount;
    public Timestamp date;

    public static boolean addTransaction(int investmentId, String type, double amount) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Transactions (investment_id,transaction_type,amount) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,investmentId);
            stmt.setString(2,type);
            stmt.setDouble(3,amount);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){ e.printStackTrace(); return false; }
    }
}
