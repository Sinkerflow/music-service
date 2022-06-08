package com.sinkerflow.service;

import java.util.List;
import java.util.UUID;

public interface CrudService<E> {

    E create(E entity);

    List<E> findAll();

    List<E> findAllByName(String name);

    E findById(UUID id);

    E update(E entity);

    void delete(UUID id);
}
