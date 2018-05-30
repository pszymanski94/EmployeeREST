package com.task.employee.service;

import com.task.employee.dto.EmployeeDTO;
import com.task.employee.entity.Employee;
import com.task.employee.entity.Role;
import com.task.employee.mapper.EmployeeMapper;
import com.task.employee.repository.EmployeeRepository;
import com.task.employee.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeMapper employeeMapper;

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Long createEmployee(EmployeeDTO employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Role role = roleRepository.findByName(employeeDto.getRole());
        employee.setRole(role);

        employeeRepository.save(employee);
        return employee.getId();
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employeeRepository.delete(employee);
    }

    public List<EmployeeDTO> search(String value, String option) {
        List<Employee> results = null;
        switch (option) {
            case "name":
                results = employeeRepository.findByName(value);
                break;
            case "subname":
                results = employeeRepository.findBySubname(value);
                break;
            case "email":
                results = new ArrayList<>();
                results.add(employeeRepository.findByEmail(value));
            default:
                throw new RuntimeException("Unknown search option");
        }

        return results.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeDTO update (Long id, EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.getOne(id);
        Role role = roleRepository.findByName(employeeDTO.getRole());

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setRole(role);

        return employeeMapper.toDto(employee);
    }
}