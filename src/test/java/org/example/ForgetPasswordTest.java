package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.*;

public class ForgetPasswordTest {
    private static AuthenticationSystem system;
    private static EmailServiceMock emailService;

    @BeforeAll
    public static void setUp() {
        system = new AuthenticationSystem();
        emailService = mock(EmailServiceMock.class);
        system.setEmailService(emailService);
    }

    @Test
    public void testForgetPassword_ValidEmail() {
        String email = "user@example.com";
        system.forgetPassword(email);

        // Verify that the email sending method is called with the correct email address
        verify(emailService, times(1)).sendPasswordResetEmail(eq(email));

        // Assert that a success message is displayed after sending the email
        assertEquals("Password reset email sent to " + email + " with new password.", system.getLastMessage());
    }

    @Test
    public void testForgetPassword_InValidEmail() {
        String email = "user1@example.com";
        system.forgetPassword(email);

        // Assert that a success message is displayed after sending the email
        assertEquals("Email address not found.", system.getLastMessage());
    }

}
