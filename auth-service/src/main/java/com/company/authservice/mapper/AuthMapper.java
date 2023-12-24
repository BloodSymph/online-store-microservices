package com.company.authservice.mapper;

import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public static SignupDto mapToSignupDto(UserEntity user) {
        return SignupDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
