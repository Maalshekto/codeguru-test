import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsecureWebService {

    // Hardcoded AWS credentials (security risk)
    private static final String ACCESS_KEY = "AKIA1234567890FAKE";
    private static final String SECRET_KEY = "abcd1234xyzFAKEKEY";

    public static void main(String[] args) {
        String userInput = "' OR 1=1 --";
        executeQuery(userInput);

        // Logging sensitive information (privacy risk)
        String userPassword = "P@ssw0rd123";
        System.out.println("User password is: " + userPassword);

        // Using default TLS settings (may be insecure)
        System.setProperty("https.protocols", "TLSv1");

        // Creating threads in an unsafe way
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println("Running thread: " + Thread.currentThread().getName());
            }).start();
        }

        // No validation on user input for file access
        String filePath = args.length > 0 ? args[0] : "/etc/passwd";
        System.out.println("Trying to read file: " + filePath);
    }

    public static void executeQuery(String input) {
        try {
            // Unparameterized query (SQL Injection risk)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE name = '" + input + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Found user: " + rs.getString("name"));
            }
        } catch (Exception e) {
            // Broad exception handling
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
