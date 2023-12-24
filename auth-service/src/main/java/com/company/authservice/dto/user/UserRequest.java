package com.company.authservice.dto.user;

import jakarta.validation.constraints.Email;
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
public class UserRequest {

    private Long version;

    @NotEmpty
    @NotBlank(message = "Username should not be empty!")
    @Length(min = 3, message = "Username should be minimum of {min} characters!")
    private String username;

    @Email
    @NotEmpty
    @NotBlank(message = "Email should not be empty!")
    private String email;

    @NotEmpty
    @NotBlank(message = "Password should not be empty!")
    // TODO: 24.12.2023 Create Password validation
    private String password;

}