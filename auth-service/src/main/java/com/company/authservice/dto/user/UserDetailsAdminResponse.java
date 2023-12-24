package com.company.authservice.dto.user;

import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.dto.role.RoleResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetailsAdminResponse extends UserAdminResponse {

    private ProfileResponse profile;

    private Set<RoleResponse> roles;

}
