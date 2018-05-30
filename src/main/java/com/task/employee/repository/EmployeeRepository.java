package com.task.employee.repository;

import com.task.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Long> {
    Employee findByEmail(String email);

    List<Employee> findByName(String name);

    List<Employee> findBySubname(String subname);



}
