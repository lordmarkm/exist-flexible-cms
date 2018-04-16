package com.mynt.core.web.validation;

import com.mynt.core.web.validation.validator.NumericValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumericValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Numeric {

    String message() default "Alphabet values are not allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
