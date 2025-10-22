import java.sql.*;
import java.util.ArrayList;

public class Scheme {
    public int schemeId;
    public String name;
    public String description;
    public float interestRate;
    public int durationMonths;
    public String riskLevel;

    // Add scheme
    public static boolean addScheme(String name, String desc, float rate, int duration, String risk) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Schemes (name,description,interest_rate,duration_months,risk_level) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setString(2,desc);
            stmt.setFloat(3,rate);
            stmt.setInt(4,duration);
            stmt.setString(5,risk);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e) { e.printStackTrace(); return false; }
    }

    // Get all schemes
    public static ArrayList<Scheme> getAllSchemes() {
        ArrayList<Scheme> list = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Schemes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Scheme s = new Scheme();
                s.schemeId = rs.getInt("scheme_id");
                s.name = rs.getString("name");
                s.description = rs.getString("description");
                s.interestRate = rs.getFloat("interest_rate");
                s.durationMonths = rs.getInt("duration_months");
                s.riskLevel = rs.getString("risk_level");
                list.add(s);
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return list;
    }
}
