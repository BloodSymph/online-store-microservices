package com.company.authservice.mapper;

import com.company.authservice.dto.user.CurrentUserDetailsResponse;
import com.company.authservice.dto.user.UserAdminResponse;
import com.company.authservice.dto.user.UserDetailsAdminResponse;
import com.company.authservice.dto.user.UserRequest;
import com.company.authservice.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.company.authservice.mapper.ProfileMapper.mapToProfileResponse;


@Component
public class UserMapper {

    public static UserAdminResponse mapToUserAdminResponse(UserEntity user) {
        return UserAdminResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UserDetailsAdminResponse mapToUserDetailsAdminResponse(UserEntity user) {
        return UserDetailsAdminResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .profile(
                        mapToProfileResponse(
                                user.getProfileEntity()
                        )
                )
                .roles(
                        user.getRoles()
                                .stream().map(RoleMapper::mapToRoleResponse)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static CurrentUserDetailsResponse mapToCurrentUserResponse(UserEntity user) {
        return CurrentUserDetailsResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .profile(
                        mapToProfileResponse(
                                user.getProfileEntity()
                        )
                )
                .build();
    }

    public static UserEntity mapToUser(UserRequest userRequest) {
        return UserEntity.builder()
                .version(userRequest.getVersion())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

}
