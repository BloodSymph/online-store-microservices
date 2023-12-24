package com.company.authservice.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    private Long version;

    @NotBlank(message = "First name should not be empty!")
    @Length(min = 3, max = 30, message = "First name should be minimum of {min} and maximum of {max} characters!")
    private String firstName;

    @NotBlank(message = "Last name should not be empty!")
    @Length(min = 3, max = 30, message = "Last name should be minimum of {min} and maximum of {max} characters!")
    private String lastName;

    @NotBlank(message = "Phone number should not be empty!")
    @Length(min = 8, max = 30, message = "Phone number should be minimum of {min} and maximum of {max} characters!")
    private String phoneNumber;

    @NotBlank(message = "Country should not be empty!")
    @Length(min = 3, max = 30, message = "Country should be minimum of {min} and maximum of {max} characters!")
    private String country;

    @NotBlank(message = "City should not be empty!")
    @Length(min = 3, max = 30, message = "City should be minimum of {min} and maximum of {max} characters!")
    private String city;

    @NotBlank(message = "Address should not be empty!")
    @Length(min = 3, max = 30, message = "Address should be minimum of {min} and maximum of {max} characters!")
    private String address;

    @NotBlank(message = "Mail address should not be empty!")
    @Length(min = 3, max = 30, message = "Mail address  be minimum of {min} and maximum of {max} characters!")
    private String mailAddress;

}
