package com.xinchen.batch.trigger;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author xinchen
 * @version 1.0
 * @date 11/06/2019 15:18
 */
public class PersonValidator<T> implements Validator<T>, InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        //使用JSR-303的Validator来校验我们的数据,在此处进行JSR-303的Validator的初始化
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(T value) throws ValidationException {
        // 使用Validator的validate方法校验数据
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value);
        if (constraintViolations.size()>0){
            StringBuilder message = new StringBuilder();
            constraintViolations.forEach((x)->message.append(x.getMessage()+"\n"));
            throw new ValidationException(message.toString());
        }

    }
}
