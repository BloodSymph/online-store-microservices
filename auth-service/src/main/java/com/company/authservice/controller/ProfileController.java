package com.company.authservice.controller;

import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/profile")
public class ProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse getProfile() {

        return userProfileService.getProfile();

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse createProfile(
            @Valid @RequestBody ProfileRequest profileRequest) {

        return userProfileService.createProfile(profileRequest);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse updateProfile(
            @Valid @RequestBody ProfileRequest profileRequest) {

        return userProfileService.updateProfile(profileRequest);

    }

    @DeleteMapping("/clean")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> cleanProfile() {

        userProfileService.deleteProfile();

        return new ResponseEntity<>(
                "Profile is cleaned!", HttpStatus.OK
        );

    }

}
