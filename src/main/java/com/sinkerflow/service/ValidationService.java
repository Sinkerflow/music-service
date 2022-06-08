package com.sinkerflow.service;

import java.util.UUID;

public interface ValidationService<T> {

    void onCreate(T entity);

    void onUpdate(T entity);

    void onDelete(UUID id);
}
