package com.mynt.core.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Min(value = 0, message = "Negative Amount is Invalid")
@Digits(integer = 19, fraction = 2, message = "Invalid Amount length")
public @interface Amount {

    String message() default "Invalid Amount";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
