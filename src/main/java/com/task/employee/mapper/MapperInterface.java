package com.task.employee.mapper;

public interface MapperInterface<E,D> {
    E toEntity(D dto);

    D toDto(E entity);


}
