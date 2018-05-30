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
public class RoleDTO {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotNull
    private String name;

    @JsonProperty(access = READ_ONLY)
    private int numberOfEmployees;

    @Builder
    public RoleDTO(Long id, String name, int numberOfEmployees) {
        this.id = id;
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
    }
}
