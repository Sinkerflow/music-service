package com.sinkerflow.music.service.validation;

import com.sinkerflow.music.service.ValidationService;

import java.util.UUID;

public class AbstractValidationService<T> implements ValidationService<T> {

    @Override
    public void validateOnCreate(T entity) {
        // implement to use
    }

    @Override
    public void validateOnUpdate(T entity) {
        // implement to use
    }

    @Override
    public void validateOnDelete(UUID id) {
        // implement to use
    }
}