package com.example.SpringBoot.Security;

import org.springframework.stereotype.Service;

@Service
public class Credentials {

    private boolean passwordValid;
    private String errorMessage;

    public Credentials() {
        this.passwordValid = true;
    }

    public void validatepassword(String password) {
        if (password.length() < 6) {
            errorMessage = "Password must be at least 6 characters long.";
            passwordValid = false;
            return;
        }

        // contains at least one number
        if (!password.matches(".*\\d.*")) {
            errorMessage = "Password must contain at least one number.";
            passwordValid = false;
            return;
        }

        // contains at least one symbol
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            errorMessage = "Password must contain at least one symbol.";
            passwordValid = false;
            return;
        }

        // Password is valid if it passes all conditions
        passwordValid = true;
    }

    public boolean isPasswordValid() {
        return passwordValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
