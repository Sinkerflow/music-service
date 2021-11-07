package com.sinkerflow.music.service;

import java.util.Collection;
import java.util.UUID;

public interface CrudService<E> {

    E create(E entity);

    Collection<E> find();

    Collection<E> find(String name);

    E findOne(String name);

    E findOne(UUID id);

    E update(E entity);

    void delete(UUID id);
}
