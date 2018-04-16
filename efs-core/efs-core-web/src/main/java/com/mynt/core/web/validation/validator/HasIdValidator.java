package com.mynt.core.web.validation.validator;

import com.mynt.core.dto.BaseInfo;
import com.mynt.core.web.validation.HasId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class HasIdValidator implements ConstraintValidator<HasId, BaseInfo> {
    @Override
    public void initialize(HasId hasId) {

    }

    @Override
    public boolean isValid(BaseInfo baseInfo, ConstraintValidatorContext constraintValidatorContext) {
        return baseInfo != null && baseInfo.getId() != null && baseInfo.getId() != 0;
    }
}
