package com.company.authservice.mapper;

import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.entity.ProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public static ProfileResponse mapToProfileResponse(ProfileEntity profileEntity){
        return ProfileResponse.builder()
                .firstName(profileEntity.getFirstName())
                .lastName(profileEntity.getLastName())
                .phoneNumber(profileEntity.getPhoneNumber())
                .country(profileEntity.getCountry())
                .address(profileEntity.getAddress())
                .mailAddress(profileEntity.getMailAddress())
                .build();
    }

    public static ProfileEntity mapToProfile(ProfileRequest profileRequest){
        return ProfileEntity.builder()
                .version(profileRequest.getVersion())
                .firstName(profileRequest.getFirstName())
                .lastName(profileRequest.getLastName())
                .phoneNumber(profileRequest.getPhoneNumber())
                .country(profileRequest.getCountry())
                .city(profileRequest.getCity())
                .address(profileRequest.getAddress())
                .mailAddress(profileRequest.getMailAddress())
                .build();
    }

}
