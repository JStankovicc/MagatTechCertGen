package com.magattech.certGen.service;

import com.magattech.certGen.model.request.SignInWithTokenRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenValidForRefresh(String token);
}