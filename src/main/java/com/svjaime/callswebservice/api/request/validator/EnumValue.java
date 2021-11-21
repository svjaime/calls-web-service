package com.svjaime.callswebservice.api.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Validation constraint for enum values.
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValueValidator.class)
@Documented
public @interface EnumValue {

    /**
     * Get the message value.
     *
     * @return the message.
     */
    String message() default "Must be any of enum {enumClass}";

    /**
     * Check if null is allowed.
     *
     * @return {@code true} if null value is allowed, {@code false} otherwise.
     */
    boolean nullable() default true;

    /**
     * Get the enum type value.
     *
     * @return The enum type.
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * Get the field name.
     *
     * @return The field name.
     */
    String fieldName();

    /**
     * Get the list of groups.
     *
     * @return The list of class groups.
     */
    Class<?>[] groups() default {};

    /**
     * Get the payload.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};
}
