package com.company.authservice.service.implementation;

import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.entity.ProfileEntity;
import com.company.authservice.entity.UserEntity;
import com.company.authservice.exception.ProfileNotFoundException;
import com.company.authservice.repository.ProfileRepository;
import com.company.authservice.repository.UserRepository;
import com.company.authservice.service.UserProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import static com.company.authservice.mapper.ProfileMapper.mapToProfile;
import static com.company.authservice.mapper.ProfileMapper.mapToProfileResponse;
import static com.company.authservice.utils.SecurityUtil.getSessionUser;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImplementation implements UserProfileService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    @Override
    public ProfileResponse getProfile() {

        String usernameFromSession = getSessionUser();

        ProfileEntity profileEntity = profileRepository
                .findByUser_Username(usernameFromSession)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "Can not find profile by current username: " + usernameFromSession
                        )
                );

        return mapToProfileResponse(profileEntity);
    }

    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) {

        String usernameFromSession = getSessionUser();

        UserEntity user = userRepository
                .findByUsername(usernameFromSession)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Username not found!"
                        )
                );

        ProfileEntity profileEntity = mapToProfile(profileRequest);
        profileEntity.setUser(user);

        profileRepository.save(profileEntity);

        return mapToProfileResponse(profileEntity);

    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) {

        String usernameFromSession = getSessionUser();

        ProfileEntity profileEntity = profileRepository
                .findByUser_Username(usernameFromSession)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "Can not find profile by current username: " + usernameFromSession
                        )
                );

        profileEntity.setFirstName(profileRequest.getFirstName());
        profileEntity.setLastName(profileRequest.getLastName());
        profileEntity.setPhoneNumber(profileRequest.getPhoneNumber());
        profileEntity.setCountry(profileRequest.getCountry());
        profileEntity.setCity(profileRequest.getCity());
        profileEntity.setAddress(profileRequest.getAddress());
        profileEntity.setMailAddress(profileRequest.getMailAddress());

        ProfileEntity updatedProfile = profileRepository.save(profileEntity);

        return mapToProfileResponse(updatedProfile);

    }

    @Override
    @Transactional
    public void deleteProfile() {

        String usernameFromSession = getSessionUser();

        if (!profileRepository.existsByUser_Username(usernameFromSession)) {
            throw new UsernameNotFoundException(
                    "User name not found!"
            );
        }

        profileRepository.deleteByUser_Username(usernameFromSession);

    }

}
