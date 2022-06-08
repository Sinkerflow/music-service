package com.sinkerflow.service.validator;

import com.sinkerflow.api.handler.exception.MultipleErrorsException;
import com.sinkerflow.api.handler.Entry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Validator<M> {

    private final List<Entry> errors = new ArrayList<>();

    public List<Entry> validate(M model) {
        throw new RuntimeException();
    }

    public Validator<M> validate(M model, Validator<M> validator) {
        List<Entry> entries = validator.validate(model);
        if (!entries.isEmpty()) {
            errors.addAll(entries);
        }
        return this;
    }

    public void build() {
        if (!errors.isEmpty()) {
            throw new MultipleErrorsException(errors);
        }
    }
}
