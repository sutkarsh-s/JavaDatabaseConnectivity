package com.example.newJDBC;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        // 🔹 Insert users
        User u1 = new User("ModernUser1", "modern1@gmail.com");
        User u2 = new User("ModernUser2", "modern2@gmail.com");

        int id1 = dao.createUser(u1);
        int id2 = dao.createUser(u2);

        System.out.println("Inserted IDs: " + id1 + ", " + id2);

        // 🔹 Fetch users
        List<User> users = dao.getAllUsers();

        System.out.println("---- USERS ----");

        for (User u : users) {
            System.out.println(
                    u.getId() + " | " +
                            u.getName() + " | " +
                            u.getEmail() + " | " +
                            u.getStatus()
            );
        }
    }
}
