package com.task.employee.mapper;

import com.task.employee.dto.EmployeeDTO;
import com.task.employee.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements MapperInterface<Employee,EmployeeDTO> {

    public Employee toEntity(EmployeeDTO employeeDTO){
        return Employee.builder()
                .email(employeeDTO.getEmail())
                .subname(employeeDTO.getSubname())
                .name(employeeDTO.getName())
                .build();
    }

    public  EmployeeDTO toDto(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .subname(employee.getSubname())
                .email(employee.getEmail())
                .role(employee.getRole().getName())
                .build();
    }
}
