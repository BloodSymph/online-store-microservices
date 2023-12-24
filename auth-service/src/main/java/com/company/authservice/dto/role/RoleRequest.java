package com.company.authservice.dto.role;

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
public class RoleRequest {

    private Long version;

    @NotEmpty
    @NotBlank(message = "Role field should not be empty!")
    @Length(max = 20, message = "Name should be maximum of {max} characters!")
    private String name;

}
