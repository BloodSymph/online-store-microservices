package com.company.authservice.service;

import com.company.authservice.dto.auth.AuthResponse;
import com.company.authservice.dto.auth.LoginDto;
import com.company.authservice.dto.auth.SignupDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    SignupDto registerNewUser(SignupDto signupDto);

    AuthResponse loginUser(LoginDto loginDto);

}
