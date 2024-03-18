package com.magattech.certGen.service;

import com.magattech.certGen.model.request.SignInWithTokenRequest;
import com.magattech.certGen.model.request.SignUpRequest;
import com.magattech.certGen.model.request.SigninRequest;
import com.magattech.certGen.model.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse signInWithToken(SignInWithTokenRequest request);
}