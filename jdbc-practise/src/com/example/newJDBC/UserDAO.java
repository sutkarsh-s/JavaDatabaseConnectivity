package com.example.newJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 🔹 CREATE USER
    public int createUser(User user) {

        String query = "INSERT INTO users (name, email) VALUES (?, ?)";

        // ✅ try-with-resources → auto closes connection + statement
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters safely (prevents SQL injection)
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());

            int rows = ps.executeUpdate(); // execute insert

            if (rows > 0) {
                // Get auto-generated ID from DB
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        user.setId(id);
                        return id;
                    }
                }
            }

        } catch (SQLException e) {
            /*
             * Better than generic Exception:
             * - Contains SQLState, vendor error codes
             * - Helps debugging production issues
             */
            e.printStackTrace();
        }

        return -1;
    }

    // 🔹 READ ALL USERS
    public List<User> getAllUsers() {

        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                // 🔥 Row Mapping (DB row → Java Object)
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
