public class TestApp {
    public static void main(String[] args) {
        try {
            // Attempt to get connection from DatabaseConnection.java
            if(DatabaseConnection.getConnection() != null) {
                System.out.println("Connected to MySQL successfully!");
            } else {
                System.out.println("Failed to connect.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
