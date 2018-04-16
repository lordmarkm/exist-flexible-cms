package com.mynt.core.web.validation;

import com.mynt.core.web.validation.validator.AlphaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlphaValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Alpha {
    String message() default "Numeric Values are not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
