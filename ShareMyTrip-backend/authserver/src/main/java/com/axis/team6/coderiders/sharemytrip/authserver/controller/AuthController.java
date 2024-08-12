package com.axis.team6.coderiders.sharemytrip.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import com.axis.team6.coderiders.sharemytrip.authserver.dto.AuthRequest;
import com.axis.team6.coderiders.sharemytrip.authserver.entity.UserCredential;
import com.axis.team6.coderiders.sharemytrip.authserver.service.AuthService;
import com.axis.team6.coderiders.sharemytrip.authserver.service.UserCredentialServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCredentialServiceImpl userCredentialService;

    //Adding new user credentials
    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        userCredentialService.saveUserCredential(user);
        return service.saveUser(user);
    }

    //Generating jwt token
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        String usernameWithUserType = authRequest.getEmail() + ":" + authRequest.getUserType();
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameWithUserType, authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmail(), authRequest.getUserType());
        } else {
            throw new RuntimeException("Invalid access");
        }
    }

    //Updating user credentials
    @PostMapping("/update")
    public String updateUser(@RequestBody UserCredential user) {
        return userCredentialService.updateUserCredential(user);
    }

    //Validating jwt token
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
