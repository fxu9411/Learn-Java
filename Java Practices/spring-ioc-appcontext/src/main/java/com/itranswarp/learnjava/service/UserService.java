package com.itranswarp.learnjava.service;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private MailService mailService;
    private DataSource dataSource;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getUserFromDB(String email, String password) {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "SELECT id, name FROM user WHERE email = ? AND password = ? LIMIT 1";
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, email);
            ps.setObject(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    return new User(id, email, password, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

//    private List<User> users = new ArrayList<>(List.of( // users:
//            new User(1, "bob@example.com", "password", "Bob"), // bob
//            new User(2, "alice@example.com", "password", "Alice"), // alice
//            new User(3, "tom@example.com", "password", "Tom"))); // tom

    public User login(String email, String password) {
//        for (User user : users) {
//            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
//                mailService.sendLoginMail(user);
//                return user;
//            }
//        }
        User user = getUserFromDB(email, password);
        if (user != null) {
            mailService.sendLoginMail(user);
            return user;
        }
        throw new RuntimeException("login failed.");
    }

//    public User getUser(long id) {
//        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
//    }

    public User registerUserToDB(User user) {
        try (Connection conn = this.dataSource.getConnection()) {
            String sql = "INSERT INTO USER(name, email, password> values(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getEmail());
            ps.setObject(3, user.getPassword());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User register(String email, String password, String name) {
//        users.forEach((user) -> {
//            if (user.getEmail().equalsIgnoreCase(email)) {
//                throw new RuntimeException("email exist.");
//            }
//        });
//        User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong(), email, password, name);
//		users.add(user);
		if (getUserFromDB(email, password) != null) {
			throw new RuntimeException("email exists");
		}
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        this.registerUserToDB(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}
