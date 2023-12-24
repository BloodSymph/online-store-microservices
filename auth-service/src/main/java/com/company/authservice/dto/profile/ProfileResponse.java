package com.company.authservice.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String country;

    private String city;

    private String address;

    private String mailAddress;

}
