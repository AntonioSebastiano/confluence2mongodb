package com.ulixe.confluence2mongodb.helpers;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.lang.NonNull;

public class HybernateValidator<T> implements Validator<T> {

    private final jakarta.validation.Validator jakartaValidator;

    public HybernateValidator(jakarta.validation.Validator jakartaValidator) {
        this.jakartaValidator = jakartaValidator;
    }

    @Override
    public void validate(@NonNull T value) throws ValidationException {
        final var result = jakartaValidator.validate(value);
        if (!result.isEmpty()) {
            throw new ValidationException("Errors: " + result);
        }
    }
}
