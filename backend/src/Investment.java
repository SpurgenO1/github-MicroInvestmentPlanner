import java.sql.*;
import java.util.ArrayList;

public class Investment {
    public int investmentId;
    public int userId;
    public int schemeId;
    public double amount;
    public Date startDate;
    public Date endDate;
    public String status;

    // Add investment
    public static boolean addInvestment(int userId,int schemeId,double amount, Date startDate, Date endDate) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Investments (user_id,scheme_id,amount,start_date,end_date,status) VALUES (?,?,?,?,?,'Active')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            stmt.setInt(2,schemeId);
            stmt.setDouble(3,amount);
            stmt.setDate(4,startDate);
            stmt.setDate(5,endDate);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){ e.printStackTrace(); return false; }
    }

    // Get investments for user
    public static ArrayList<Investment> getUserInvestments(int userId) {
        ArrayList<Investment> list = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Investments WHERE user_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Investment inv = new Investment();
                inv.investmentId = rs.getInt("investment_id");
                inv.schemeId = rs.getInt("scheme_id");
                inv.amount = rs.getDouble("amount");
                inv.startDate = rs.getDate("start_date");
                inv.endDate = rs.getDate("end_date");
                inv.status = rs.getString("status");
                list.add(inv);
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }
}
