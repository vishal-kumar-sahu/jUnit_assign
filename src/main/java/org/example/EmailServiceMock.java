package org.example;

public class EmailServiceMock implements EmailService {
    @Override
    public void sendPasswordResetEmail(String email) {
        System.out.println("Password reset email sent to " + email + " with new password.");
    }
}