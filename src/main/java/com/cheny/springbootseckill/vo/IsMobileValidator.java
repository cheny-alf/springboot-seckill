package com.cheny.springbootseckill.vo;

import com.cheny.springbootseckill.utils.ValidatorUtil;
import com.cheny.springbootseckill.validator.IsMobile;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;

/**
 * 这是用来规范手机号验证规则
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidatorUtil.isMobile(s);
        }else{
            if(StringUtils.isEmpty(s)){
                return true;
            }else{
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
