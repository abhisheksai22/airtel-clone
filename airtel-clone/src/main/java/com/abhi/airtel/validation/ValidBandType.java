package com.abhi.airtel.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BandTypeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBandType {
    String message() default "{band.type.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}