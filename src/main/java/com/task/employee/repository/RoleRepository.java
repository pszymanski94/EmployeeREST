package com.task.employee.repository;

import com.task.employee.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByName(String name);
}
