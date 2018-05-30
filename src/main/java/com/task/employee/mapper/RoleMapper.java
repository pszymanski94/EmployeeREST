package com.task.employee.mapper;

import com.task.employee.dto.RoleDTO;
import com.task.employee.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleDTO roleDTO){
        return Role.builder()
                .name(roleDTO.getName())
                .build();
    }

    public  RoleDTO toDto(Role role){
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .numberOfEmployees(role.getEmployees().size())
                .build();
    }
}
