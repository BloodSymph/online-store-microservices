package com.company.authservice.service;

import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.dto.role.RoleRequest;
import com.company.authservice.dto.role.RoleResponse;
import com.company.authservice.dto.user.UserAdminResponse;
import com.company.authservice.dto.user.UserDetailsAdminResponse;
import com.company.authservice.dto.user.UserRequest;
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

    UserAdminResponse addNewUserWithAdminPermission(UserRequest userRequest);

    void deleteUser(String username);

    /*
        Role admin methods
    */

    RoleResponse addNewRole(RoleRequest roleRequest);

    void deleteRole(String name);

    /*
        Profile admin methods
    */

}
