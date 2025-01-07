package com.jwt.service;

import org.springframework.security.core.Authentication;

public interface IJwtService {

    String generateToken(Authentication authentication);

    boolean validateToken(String token);

    String getUsernameFromJwtToken(String token);
}
