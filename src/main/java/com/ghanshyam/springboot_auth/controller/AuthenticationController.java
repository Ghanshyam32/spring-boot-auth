package com.ghanshyam.springboot_auth.controller;

import com.ghanshyam.springboot_auth.dto.LoginUserDto;
import com.ghanshyam.springboot_auth.dto.RegisterUserDto;
import com.ghanshyam.springboot_auth.dto.VerifyUserDto;
import com.ghanshyam.springboot_auth.model.User;
import com.ghanshyam.springboot_auth.response.LoginResponse;
import com.ghanshyam.springboot_auth.service.AuthenticationService;
import com.ghanshyam.springboot_auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtService.getExpirationTime(), jwtToken);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account Verified Successfully");
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.badRequest().body(runtimeException.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        try {
            authenticationService.resentVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.badRequest().body(runtimeException.getMessage());
        }
    }

}
