package com.example.SpringBoot.Security;

import java.util.Base64;


// my JDK 21 has configration errors, i tried very long to fix it but not working in any way

public class TokenGenerator {

    // Secret key for token generation
    private static final String SECRET_KEY = "secret";
    // take username as token encrypt it, put it in session, then decrypt it in when recieving request

    // if signed in
    // generate token
    // html will save cookie
    // save in cookies and send in requests
    // validate token and decrypt
    // if credentials
    public static String generateToken(String username) {
        // concatenate username with secret key thne Encode the token using Base64 encoding
        String token = username + ":" + SECRET_KEY;

        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    public static String validateToken(String token) {
        // Decode the token using Base64 decoding  Convert the decoded bytes back to a string
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedToken = new String(decodedBytes);
        // Split the decoded token to extract username and secret key
        String[] parts = decodedToken.split(":");
        if (parts.length == 2 && parts[1].equals(SECRET_KEY)) {
            return parts[0]; // Return the username if the secret key matches
        } else {
            return null; // Return null if the token is invalid
        }
    }
}
