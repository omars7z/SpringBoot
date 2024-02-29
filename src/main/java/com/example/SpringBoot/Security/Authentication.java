package com.example.SpringBoot.Security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
public class Authentication {
    private boolean isAuthenticated = false;
    private int authenticatedId = -1;
    private String authenticatedName = "";
}