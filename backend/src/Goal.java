import java.sql.*;
import java.util.ArrayList;

public class Goal {
    public int goalId;
    public int userId;
    public String goalName;
    public double targetAmount;
    public double savedAmount;
    public Date targetDate;
    public String status;

    public static boolean addGoal(int userId,String name,double target,Date targetDate) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Goals (user_id,goal_name,target_amount,target_date,status) VALUES (?,?,?,?, 'Active')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,userId);
            stmt.setString(2,name);
            stmt.setDouble(3,target);
            stmt.setDate(4,targetDate);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){ e.printStackTrace(); return false; }
    }
}
