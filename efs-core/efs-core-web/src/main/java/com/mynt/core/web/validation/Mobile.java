package com.mynt.core.web.validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Numeric
@Length(min = 11, max = 11, message = "Mobile Number should be 11 digits only")
public @interface Mobile {

    String message() default "Mobile Number should be 11 digits only";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
