package com.jimmyproject.bookingservice.util;

import com.jimmyproject.bookingservice.exception.ValidationException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ValidationUtils {

    public static void validate(boolean condition, String message) {
        if (!condition) {
            throw new ValidationException(message);
        }
    }

    public static void validateNotBlank(String value, String fieldName) {
        if (!StringUtils.hasText(value)) {
            throw new ValidationException(fieldName + " cannot be blank");
        }
    }

    public static <T> T validateEntityExists(Supplier<T> supplier, String entityName, String fieldName, Object fieldValue) {
        T entity = supplier.get();
        if (entity == null) {
            throw new ValidationException(String.format("%s with %s '%s' does not exist", 
                entityName, fieldName, fieldValue));
        }
        return entity;
    }

    public static void validateAll(List<String> validations) {
        List<String> errors = new ArrayList<>();
        for (String validation : validations) {
            if (validation != null) {
                errors.add(validation);
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
