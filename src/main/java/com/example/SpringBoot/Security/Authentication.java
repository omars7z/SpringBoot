// Authentication.java

package com.example.SpringBoot.Security;

import com.example.SpringBoot.Data.StudentDAOImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@Getter
public class Authentication {
    private boolean isAuthenticated = false;
    private int authenticatedId = -1;
    private String authenticatedName = "";
    private String authenticatedUsername = "";
    private List<String> authenticatedUsernames;

    @Autowired
    private StudentDAOImpl studentDAOImpl;

    public void setAuthenticatedUsername(String username) {
        this.authenticatedUsername = username;
    }

    public String getAuthenticatedUsername() {
        return authenticatedUsername;
    }

    public List<String> getAuthenticatedUsernames() {
        authenticatedUsernames = studentDAOImpl.getAllUsernames();
        return authenticatedUsernames;
    }
}
