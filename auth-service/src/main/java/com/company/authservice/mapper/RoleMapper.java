package com.company.authservice.mapper;

import com.company.authservice.dto.role.RoleRequest;
import com.company.authservice.dto.role.RoleResponse;
import com.company.authservice.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public static RoleResponse mapToRoleResponse(RoleEntity role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }

    public static RoleEntity mapToRole(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .version(roleRequest.getVersion())
                .name(roleRequest.getName())
                .build();
    }

}
