package com.useful.person.core.fieldcheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.useful.person.core.utils.IdCardUtil;

public class IDCardNoValidRule implements ConstraintValidator<NotValidIDCardNo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return IdCardUtil.checkIdCardNo(value);
    }

}
