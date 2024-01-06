package com.company.authservice.service;

import com.company.authservice.dto.auth.SignupDto;
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

    /*
        User admin methods
    */

    Page<UserAdminResponse> getAllUsers(Pageable pageable);

    UserDetailsAdminResponse getUserDetails(String username);

    Page<UserAdminResponse> searchUsers(
            Pageable pageable, String username
    );

    UserAdminResponse updateUserInformation(
            UserRequest userRequest, String username
    );

    UserDetailsAdminResponse givePermissionForUser(
            String username, String name
    );

    void deletePermissionForUser(
            String username
    );

    void deleteUser(String username);

    /*
        Role admin methods
    */

    Page<RoleResponse> getAllRoles(Pageable pageable);

    RoleResponse addNewRole(RoleRequest roleRequest);

    RoleResponse updateCurrentRole(
            RoleRequest roleRequest, String name
    );

    void deleteRole(String name);

    /*
        Profile admin methods
    */

}
