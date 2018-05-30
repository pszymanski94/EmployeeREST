package com.task.employee.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String subname;

    private String email;

    @ManyToOne
    @JoinColumn(name = "ROLE", nullable = false)
    private Role role;

    @Builder
    public Employee(String name, String subname, String email){
        this.email = email;
        this.subname=subname;
        this.name = name;
    }
}
