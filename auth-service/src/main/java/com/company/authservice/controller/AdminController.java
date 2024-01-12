package com.company.authservice.controller;

import com.company.authservice.dto.auth.SignupDto;
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

    /*
        User controller methods!
    */

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



    /*
        Role controller methods!
    */

    /*
        Profile controller methods!
    */

}
