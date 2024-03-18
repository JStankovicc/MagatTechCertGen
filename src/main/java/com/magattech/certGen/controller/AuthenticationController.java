package com.magattech.certGen.controller;

import com.magattech.certGen.model.request.SignInWithTokenRequest;
import com.magattech.certGen.model.request.SignUpRequest;
import com.magattech.certGen.model.request.SigninRequest;
import com.magattech.certGen.model.response.JwtAuthenticationResponse;
import com.magattech.certGen.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/token")
    public ResponseEntity<JwtAuthenticationResponse> signInWithToken(@RequestBody SignInWithTokenRequest request){
        JwtAuthenticationResponse response = authenticationService.signInWithToken(request);
        if(response.getToken().equals("FALSE")){
            return ResponseEntity.status(403).build();
        }else return ResponseEntity.ok(response);
    }
}