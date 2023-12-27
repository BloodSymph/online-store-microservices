package com.company.authservice.controller;

import com.company.authservice.dto.auth.AuthResponse;
import com.company.authservice.dto.auth.LoginDto;
import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/client")
public class AuthController {

    private AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignupDto signup(
            @Valid @RequestBody SignupDto signupDto) {

        return authService.registerNewUser(signupDto);

    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(
            @Valid @RequestBody LoginDto loginDto) {

        return authService.loginUser(loginDto);

    }

}
