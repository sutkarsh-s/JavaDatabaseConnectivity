package com.example.newJDBC;
import java.sql.Timestamp;

public class User {

    private int id;
    private String name;
    private String email;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email, String status,
                Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
}
