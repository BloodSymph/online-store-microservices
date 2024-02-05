package com.company.authservice.service;

import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.dto.role.RoleRequest;
import com.company.authservice.dto.role.RoleResponse;
import com.company.authservice.dto.user.UserAdminResponse;
import com.company.authservice.dto.user.UserDetailsAdminResponse;
import com.company.authservice.dto.user.UserRequest;
import com.company.authservice.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Page<UserAdminResponse> getAllUsers(Pageable pageable);

    UserDetailsAdminResponse getUserDetails(String username);

    Page<UserAdminResponse> searchUsers(
            Pageable pageable, String username
    );

    UserAdminResponse updateUserInformation(
            UserRequest userRequest, String username
    );

    UserDetailsAdminResponse givePermissionForUser(
            String username, String name, Long userVersion
    );

    void removePermissionForUser(
            String username, String name, Long userVersion
    );

    void removeAllPermissionsForUser(
            String username, Long userVersion
    );

    void deleteUser(String username);

    Page<RoleResponse> getAllRoles(Pageable pageable);

    RoleResponse addNewRole(RoleRequest roleRequest);

    RoleResponse updateCurrentRole(
            RoleRequest roleRequest, String name
    );

    void deleteRole(String name);

    ProfileResponse getProfileOfUser(String username);

    ProfileResponse createProfileForUser(
            ProfileRequest profileRequest, String username
    );

    ProfileResponse updateProfileForUser(
            ProfileRequest profileRequest, String username
    );

    void deleteProfileForUser(String username);

}
