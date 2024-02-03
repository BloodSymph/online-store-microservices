package com.company.authservice.controller;

import com.company.authservice.dto.auth.SignupDto;
import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.dto.role.RoleRequest;
import com.company.authservice.dto.role.RoleResponse;
import com.company.authservice.dto.user.UserAdminResponse;
import com.company.authservice.dto.user.UserDetailsAdminResponse;
import com.company.authservice.dto.user.UserRequest;
import com.company.authservice.service.AdminService;
import com.company.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/admin")
public class AdminController {

    private final AdminService adminService;

    private final AuthService authService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> getUsers(
            @PageableDefault(
                    sort = "username",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable) {

        return adminService.getAllUsers(pageable);

    }

    @GetMapping("/users/{username}/details")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailsAdminResponse getUserDetails(
            @PathVariable(value = "username") String username) {

        return adminService.getUserDetails(username);

    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> searchUsers(
            @PageableDefault(
                    sort = "username",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable,
            @RequestParam(value = "username", required = false) String username) {

        return adminService.searchUsers(
                pageable, username
        );

    }

    @PostMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public SignupDto signup(
            @Valid @RequestBody SignupDto signupDto) {

        return authService.registerNewUser(signupDto);

    }

    @PutMapping("/users/{username}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAdminResponse updateUserInformation(
            @Valid @RequestBody UserRequest userRequest,
            @PathVariable(value = "username") String username) {

        return adminService.updateUserInformation(
                userRequest, username
        );

    }

    @PostMapping("/users/{username}/give-permission")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailsAdminResponse givePermissionForUser(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "name", required = true) String name) {

        return adminService.givePermissionForUser(
                username, name
        );

    }

    @PostMapping("/users/{username}/remove-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removePermissionForUser(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "name", required = true) String name) {

        adminService.removePermissionForUser(
                username, name
        );

        return new ResponseEntity<>(
                "Successful role removed!", HttpStatus.OK
        );

    }

    @PostMapping("/users/{username}/remove-all-permissions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeAllPermissionsFromUser(
            @PathVariable(value = "username") String username) {

        adminService.removeAllPermissionsForUser(username);

        return new ResponseEntity<>(
                "Successful roles removed!", HttpStatus.OK
        );

    }

    @DeleteMapping("/users/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(
            @PathVariable(value = "username") String username) {

        adminService.deleteUser(username);

        return new ResponseEntity<>(
                "Successful deleted!", HttpStatus.OK
        );

    }

    @GetMapping("/users/{username}/profile")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getUserProfile(
            @PathVariable(name = "username") String username) {

        return adminService.getProfileOfUser(username);

    }

    @PostMapping("/users/{username}/create-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse createProfileForUser(
            @PathVariable(name = "username") String username,
            @Valid @RequestBody ProfileRequest profileRequest) {

        return adminService.createProfileForUser(
                profileRequest, username
        );

    }
    @PutMapping("/users/{username}/update-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse updateProfileForUser(
            @PathVariable(name = "username") String username,
            @Valid @RequestBody ProfileRequest profileRequest) {

        return adminService.updateProfileForUser(
                profileRequest, username
        );

    }

    @DeleteMapping("/users/{username}/delete-profile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProfileForUser(
            @PathVariable(name = "username") String username) {

        adminService.deleteProfileForUser(username);

        return new ResponseEntity<>(
                "Successful deleted!", HttpStatus.OK
        );

    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleResponse> getAllRoles(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10
            ) Pageable pageable) {

        return adminService.getAllRoles(pageable);

    }

    @PostMapping("/roles/add-new-role")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse addNewRole(
            @Valid @RequestBody RoleRequest roleRequest) {

        return adminService.addNewRole(roleRequest);

    }

    @PutMapping("/roles/{roleName}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse updateRole(
            @PathVariable(name = "roleName") String roleName,
            @Valid @RequestBody RoleRequest roleRequest) {

        return adminService.updateCurrentRole(
                roleRequest, roleName
        );

    }

    @DeleteMapping("/roles/{roleName}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRole(
            @PathVariable(name = "roleName") String roleName) {

        adminService.deleteRole(roleName);

        return new ResponseEntity<>(
                "Successful deleted!", HttpStatus.OK
        );

    }

}
