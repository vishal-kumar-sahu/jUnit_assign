package org.example;

import java.util.*;
import java.util.UUID;

public class AuthenticationSystem {
    private Map<String, User> users = new HashMap<>();
    private EmailService emailService;
    private String lastMessage;

    public AuthenticationSystem() {
        // dummy user
        users.put("user@example.com", new User("user@example.com", "password123"));
    }

    // Method to set the email service
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void forgetPassword(String email) {
        if (users.containsKey(email)) {
            String newPassword = UUID.randomUUID().toString();
            users.get(email).setPassword(newPassword);

            emailService.sendPasswordResetEmail(email);

            lastMessage = "Password reset email sent to " + email + " with new password.";
            System.out.println(lastMessage);
        } else {
            lastMessage = "Email address not found.";
            System.out.println(lastMessage);
        }
    }

    public boolean login(String email, String password) {
        if (users.containsKey(email) && users.get(email).getPassword().equals(password)) {
            // Successful login
            lastMessage = "User logged in successfully. Redirecting to the dashboard...";
            System.out.println(lastMessage);
            return true;
        } else {
            // Login failed
            lastMessage = "Invalid email address or password.";
            System.out.println(lastMessage);
            return false;
        }
    }
}
class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
