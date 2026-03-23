package com.example.oldJDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public int createUser(User user) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int generatedId = -1;

        try {
            conn = DBConnection.getConnection();

            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                    user.setId(generatedId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ✅ Cleaner cleanup
            DBConnection.close(rs);
            DBConnection.close(ps);
            DBConnection.close(conn);
        }

        return generatedId;
    }

    public List<User> getAllUsers() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<User> users = new ArrayList<User>();

        try {
            conn = DBConnection.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }

        return users;
    }
}
