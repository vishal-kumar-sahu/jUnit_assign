package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class LoginTest {
    private static AuthenticationSystem system;

    @BeforeAll
    public static void setUp() {
        system = new AuthenticationSystem();
    }

    @Test
    public void testLogin_Valid() {
        String email = "user@example.com";
        String password = "password123";

        assertTrue(system.login(email, password));
        assertEquals("User logged in successfully. Redirecting to the dashboard...", system.getLastMessage());
    }

    @Test
    public void testLogin_InvalidEmail() {
        String email = "invalid@example.com";
        String password = "password123";

        assertFalse(system.login(email, password));
        assertEquals("Invalid email address or password.", system.getLastMessage());
    }

    @Test
    public void testLogin_InvalidPassword() {
        String email = "user@example.com";
        String wrongPassword = "wrongPassword";

        assertFalse(system.login(email, wrongPassword));
        assertEquals("Invalid email address or password.", system.getLastMessage());
    }

}
