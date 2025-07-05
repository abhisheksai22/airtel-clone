package com.abhi.airtel.validation;

import com.abhi.airtel.entity.BandType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BandTypeValidator implements ConstraintValidator<ValidBandType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        for (BandType type : BandType.values()) {
            if (type.name().equals(value) || type.getLabel().equals(value)) {
                return true;
            }
        }
        return false;
    }
}