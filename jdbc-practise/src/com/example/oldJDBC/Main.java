package com.example.oldJDBC;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        User u1 = new User("StaticUser1", "s1@gmail.com");
        User u2 = new User("StaticUser2", "s2@gmail.com");

        dao.createUser(u1);
        dao.createUser(u2);

        List<User> users = dao.getAllUsers();

        for (User u : users) {
            System.out.println(u.getId() + " " + u.getName());
        }
    }
}
