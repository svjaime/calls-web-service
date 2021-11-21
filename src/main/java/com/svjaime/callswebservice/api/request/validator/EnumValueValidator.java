package com.svjaime.callswebservice.api.request.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Validation constraint for enum values.
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private String fieldName;
    private boolean nullable;
    private List<String> acceptedValues;

    @Override
    public void initialize(final EnumValue constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        nullable = constraintAnnotation.nullable();
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintContext) {
        return Optional.ofNullable(value)
                .map(String::toUpperCase)
                .map(acceptedValues::contains)
                .orElse(nullable);
    }

    public String getFieldName() {
        return fieldName;
    }
}
