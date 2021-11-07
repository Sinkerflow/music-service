package com.sinkerflow.music.service;

import java.util.UUID;

public interface ValidationService<T> {

    void validateOnCreate(T entity);

    void validateOnUpdate(T entity);

    void validateOnDelete(UUID id);
}
