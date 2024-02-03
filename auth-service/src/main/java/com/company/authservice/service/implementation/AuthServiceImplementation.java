package com.company.authservice.service.implementation;

import com.company.authservice.dto.auth.AuthResponse;
import com.company.authservice.dto.auth.LoginDto;
import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.entity.RoleEntity;
import com.company.authservice.entity.UserEntity;
import com.company.authservice.exception.RoleNotFoundException;
import com.company.authservice.exception.UsernameIsTakenException;
import com.company.authservice.repository.RoleRepository;
import com.company.authservice.repository.UserRepository;
import com.company.authservice.security.JWTGenerator;
import com.company.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.company.authservice.mapper.AuthMapper.mapToSignupDto;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTGenerator jwtGenerator;

    @Override
    public SignupDto registerNewUser(SignupDto signupDto) {

        if (userRepository.existsByUsernameIgnoreCase(signupDto.getUsername())) {
            throw new UsernameIsTakenException(
                    "Username: " + signupDto.getUsername() + "is taken!"
            );
        }

        UserEntity user = new UserEntity();

        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        signupDto.getPassword()
                )
        );

        RoleEntity role = roleRepository
                .findByNameIgnoreCase("ROLE_USER")
                .orElseThrow(
                () -> new RoleNotFoundException(
                        "Role name dose not exist"
                )
        );

        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

        return mapToSignupDto(user);

    }

    @Override
    public AuthResponse loginUser(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generatedToken(authentication);

        return new AuthResponse(token);
    }

}
