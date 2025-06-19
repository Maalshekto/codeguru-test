public class VulnerableExample {
    public static void main(String[] args) {
        String accessKey = "AKIA1234567890FAKE";  // Credential exposé

        String userInput = "'; DROP TABLE users; --";
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'"; // Injection SQL

        System.out.println("Query: " + query);

        for (int i = 0; i < 100000; i++) { // Boucle inefficace
            System.out.println("Iteration " + i);
        }
    }
}
