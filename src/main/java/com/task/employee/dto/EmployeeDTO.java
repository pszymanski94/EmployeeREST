package com.task.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String subname;

    @NotNull
    private String email;

    @NotNull
    private String role;

    @Builder
    public EmployeeDTO(Long id, String name, String subname, String email, String role) {
        this.id = id;
        this.name = name;
        this.subname = subname;
        this.email = email;
        this.role = role;
    }
}
