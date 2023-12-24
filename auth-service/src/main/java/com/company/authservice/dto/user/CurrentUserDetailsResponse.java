package com.company.authservice.dto.user;

import com.company.authservice.dto.profile.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserDetailsResponse {

    private String username;

    private String email;

    private ProfileResponse profile;

}
