package com.company.authservice.service.implementation;

import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.dto.role.RoleRequest;
import com.company.authservice.dto.role.RoleResponse;
import com.company.authservice.dto.user.UserAdminResponse;
import com.company.authservice.dto.user.UserDetailsAdminResponse;
import com.company.authservice.dto.user.UserRequest;
import com.company.authservice.entity.RoleEntity;
import com.company.authservice.entity.UserEntity;
import com.company.authservice.exception.RoleNotFoundException;
import com.company.authservice.exception.UsernameIsTakenException;
import com.company.authservice.mapper.UserMapper;
import com.company.authservice.repository.ProfileRepository;
import com.company.authservice.repository.RoleRepository;
import com.company.authservice.repository.UserRepository;
import com.company.authservice.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.company.authservice.mapper.UserMapper.mapToUserDetailsAdminResponse;


@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Page<UserAdminResponse> getAllUsers(Pageable pageable) {

        return userRepository
                .findAll(pageable)
                .map(UserMapper::mapToUserAdminResponse);

    }


    @Override
    public UserDetailsAdminResponse getUserDetails(String username) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username
                        )
                );

        return mapToUserDetailsAdminResponse(user);

    }

    @Override
    public Page<UserAdminResponse> searchUsers(
            Pageable pageable, String username) {

        return userRepository.searchByUsernameIgnoreCase(
                pageable, username
        ).map(UserMapper::mapToUserAdminResponse);

    }

    @Override
    public UserAdminResponse addNewUserWithAdminPermission(UserRequest userRequest) {

        return null;

    }

    @Override
    @Transactional
    public void deleteUser(String username) {

        if (!userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UsernameNotFoundException(
                "Username not found!"
            );
        }

        userRepository.deleteByUsernameIgnoreCase(username);

    }

    @Override
    public RoleResponse addNewRole(RoleRequest roleRequest) {
        return null;
    }

    @Override
    @Transactional
    public void deleteRole(String name) {

    }

}
