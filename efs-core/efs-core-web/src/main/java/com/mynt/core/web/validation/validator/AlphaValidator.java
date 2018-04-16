package com.mynt.core.web.validation.validator;

import com.mynt.core.web.validation.Alpha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaValidator implements ConstraintValidator<Alpha, String> {

    private static final String PATTERN = "^[ña-zA-Z ,.'-]+$";

    @Override
    public void initialize(Alpha alpha) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        }
        return false;
    }
}
