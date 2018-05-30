package com.task.employee.service;

import com.task.employee.dto.RoleDTO;
import com.task.employee.entity.Role;
import com.task.employee.mapper.RoleMapper;
import com.task.employee.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;


    public List<RoleDTO> findAll(){
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());

    }

    public Long createRole(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);

        roleRepository.save(role);
        return role.getId();
    }
    public void deleteRole(Long roleId) {
        Role role = roleRepository.getOne(roleId);
        roleRepository.delete(role);
    }

    public RoleDTO search(String value, String option) {
        Role results = null;
        switch (option) {
            case "id":
                results = roleRepository.getOne(Long.parseLong(value));
                break;
            case "name":
                results = roleRepository.findByName(value);
                break;
            default:
                throw new RuntimeException("Unknown search option");
        }

        return roleMapper.toDto(results);
    }

    public RoleDTO update (Long id, RoleDTO roleDTO){
        Role role = roleRepository.getOne(id);

        role.setName(roleDTO.getName());
        return roleMapper.toDto(role);
    }
}
