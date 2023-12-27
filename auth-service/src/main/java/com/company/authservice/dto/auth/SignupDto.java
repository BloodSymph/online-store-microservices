package com.company.authservice.dto.auth;

import com.company.authservice.validator.ValidPassword;
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
public class SignupDto {

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
    @ValidPassword
    private String password;

}
