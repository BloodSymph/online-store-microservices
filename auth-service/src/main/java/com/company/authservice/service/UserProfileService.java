package com.company.authservice.service;

import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {

    ProfileResponse getProfile();

    ProfileResponse createProfile(ProfileRequest profileRequest);

    ProfileResponse updateProfile(ProfileRequest profileRequest);

    void deleteProfile();

}
