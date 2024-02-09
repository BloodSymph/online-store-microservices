package com.company.authservice.service.implementation;

import com.company.authservice.dto.profile.ProfileRequest;
import com.company.authservice.dto.profile.ProfileResponse;
import com.company.authservice.dto.role.RoleRequest;
import com.company.authservice.dto.role.RoleResponse;
import com.company.authservice.dto.user.UserAdminResponse;
import com.company.authservice.dto.user.UserDetailsAdminResponse;
import com.company.authservice.dto.user.UserRequest;
import com.company.authservice.entity.ProfileEntity;
import com.company.authservice.entity.RoleEntity;
import com.company.authservice.entity.UserEntity;
import com.company.authservice.exception.InvalidVersionException;
import com.company.authservice.exception.ProfileNotFoundException;
import com.company.authservice.exception.RoleNotFoundException;
import com.company.authservice.mapper.RoleMapper;
import com.company.authservice.mapper.UserMapper;
import com.company.authservice.repository.ProfileRepository;
import com.company.authservice.repository.RoleRepository;
import com.company.authservice.repository.UserRepository;
import com.company.authservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import static com.company.authservice.mapper.ProfileMapper.mapToProfile;
import static com.company.authservice.mapper.ProfileMapper.mapToProfileResponse;
import static com.company.authservice.mapper.RoleMapper.mapToRole;
import static com.company.authservice.mapper.RoleMapper.mapToRoleResponse;
import static com.company.authservice.mapper.UserMapper.mapToUserAdminResponse;
import static com.company.authservice.mapper.UserMapper.mapToUserDetailsAdminResponse;


@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Page<UserAdminResponse> getAllUsers(Pageable pageable) {

        return userRepository
                .findAll(pageable)
                .map(UserMapper::mapToUserAdminResponse);

    }


    @Override
    public UserDetailsAdminResponse getUserDetails(String username) {

        UserEntity user = userRepository
                .getUserDetails(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username + " !"
                        )
                );

        return mapToUserDetailsAdminResponse(user);

    }

    @Override
    public Page<UserAdminResponse> searchUsers(
            Pageable pageable, String username) {

        return userRepository.searchByUsernameIgnoreCase(
                pageable, username
        ).map(UserMapper::mapToUserAdminResponse);

    }

    @Override
    @Transactional
    public UserAdminResponse updateUserInformation(
            UserRequest userRequest, String username) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username + " !"
                        )
                );

        if (!userRequest.getVersion().equals(user.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        userRequest.getPassword()
                )
        );

        UserEntity updatedUserInformation = userRepository.save(user);

        return mapToUserAdminResponse(updatedUserInformation);

    }

    @Override
    @Transactional
    public UserDetailsAdminResponse givePermissionForUser(
            String username, String name, Long userVersion) {

        UserEntity user = userRepository
                .getUserDetails(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username + " !"
                        )
                );

        if (!user.getVersion().equals(userVersion)) {
            throw new InvalidVersionException(
                    "Bad request for giving permission! Invalid Entity Version!"
            );
        }

        RoleEntity role = roleRepository
                .findByNameIgnoreCase(name)
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find rol with current name: " + name + " !"
                        )
                );

        user.getRoles().add(role);

        UserEntity updatedUserRole = userRepository.save(user);

        return mapToUserDetailsAdminResponse(updatedUserRole);

    }

    @Override
    @Transactional
    public void removePermissionForUser(
            String username, String name, Long userVersion) {

        UserEntity user = userRepository
                .getUserDetails(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username + " !"
                        )
                );

        if (!user.getVersion().equals(userVersion)) {
            throw new InvalidVersionException(
                    "Bad request for delete permission! Invalid Entity Version!"
            );
        }

        RoleEntity role = roleRepository
                .findByNameIgnoreCase(name)
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find rol with current name: " + name + " !"
                        )
                );

        user.getRoles().remove(role);

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void removeAllPermissionsForUser(
            String username, Long userVersion) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username + " !"
                        )
                );

        if (!user.getVersion().equals(userVersion)) {
            throw new InvalidVersionException(
                    "Bad request for delete permissions! Invalid Entity Version!"
            );
        }

        user.getRoles().clear();

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void deleteUser(String username) {

        if (!userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UsernameNotFoundException(
                    "Can not find user with current username: " + username + " !"
            );
        }

        userRepository.deleteByUsernameIgnoreCase(username);

    }

    @Override
    public Page<RoleResponse> getAllRoles(Pageable pageable) {

        return roleRepository
                .findAll(pageable)
                .map(RoleMapper::mapToRoleResponse);

    }


    @Override
    public RoleResponse addNewRole(RoleRequest roleRequest) {

        RoleEntity role = mapToRole(roleRequest);

        roleRepository.save(role);

        return mapToRoleResponse(role);
    }

    @Override
    @Transactional
    public RoleResponse updateCurrentRole(
            RoleRequest roleRequest, String name) {

        RoleEntity role = roleRepository
                .findByNameIgnoreCase(name)
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find rol with current name: " + name + " !"
                        )
                );

        if(!roleRequest.getVersion().equals(role.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

        role.setName(roleRequest.getName());

        RoleEntity updatedRole = roleRepository.save(role);

        return mapToRoleResponse(updatedRole);

    }

    @Override
    @Transactional
    public void deleteRole(String name) {

        if (!roleRepository.existsByNameIgnoreCase(name)) {
            throw new RoleNotFoundException(
                    "Can not find rol with current name: " + name + " !"
            );
        }

        roleRepository.deleteByNameIgnoreCase(name);

    }

    @Override
    public ProfileResponse getProfileOfUser(String username) {

        ProfileEntity profileEntity = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "Can not find user profile by current username: " + username + " !"
                        )
                );

        return mapToProfileResponse(profileEntity);

    }

    @Override
    @Transactional
    public ProfileResponse createProfileForUser(
            ProfileRequest profileRequest, String username) {

        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user with current username: " + username + " !"
                        )
                );

        ProfileEntity profileEntity = mapToProfile(profileRequest);
        profileEntity.setUser(user);

        profileRepository.save(profileEntity);

        return mapToProfileResponse(profileEntity);

    }

    @Override
    @Transactional
    public ProfileResponse updateProfileForUser(
            ProfileRequest profileRequest, String username) {

        ProfileEntity profileEntity = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new ProfileNotFoundException(
                                "Can not find profile by current username: " + username + " !"
                        )
                );

        if (!profileEntity.getVersion().equals(profileRequest.getVersion())) {
            throw new InvalidVersionException(
                    "Bad request for update! Invalid Entity Version!"
            );
        }

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
    public void deleteProfileForUser(String username) {

        if (!profileRepository.existsByUser_Username(username)) {
            throw new ProfileNotFoundException(
                    "Can not find user profile by current username: " + username + " !"
            );
        }

        profileRepository.deleteByUser_Username(username);

    }

}
