package com.example.springJDBC.repository;

import com.example.springJDBC.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection (recommended)
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔹 INSERT
    public int insertUser(User user) {
        String sql = "INSERT INTO users (name, email, status) VALUES (?, ?, 'ACTIVE')";

        return jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }

    // 🔹 FETCH ALL
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                )
        );
    }

    // 🔹 FETCH BY ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                ),
                id
        );
    }

    // 🔹 UPDATE
    public int updateUserStatus(int id, String status) {
        String sql = "UPDATE users SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    // 🔹 DELETE
    public int deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}