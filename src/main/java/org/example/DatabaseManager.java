package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/get_phone";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void saveData(String link, String subject, String type) {
        String query = "INSERT INTO scraped_data (link, subject, type) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, link);
            statement.setString(2, subject);
            statement.setString(3, type);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}