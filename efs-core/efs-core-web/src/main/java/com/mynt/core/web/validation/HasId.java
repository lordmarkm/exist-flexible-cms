package com.mynt.core.web.validation;

import com.mynt.core.web.validation.validator.HasIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasId {

    String message() default "Object Id is required";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
