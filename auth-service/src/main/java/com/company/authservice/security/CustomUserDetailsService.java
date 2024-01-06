package com.company.authservice.security;

import com.company.authservice.entity.RoleEntity;
import com.company.authservice.entity.UserEntity;
import com.company.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private Collection<GrantedAuthority> mapRolesToAuthorities(Set<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(
                        role -> new SimpleGrantedAuthority(
                                role.getName()
                        )
                ).collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username
                        )
                );

        return new User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(
                        user.getRoles()
                )
        );

    }

}
