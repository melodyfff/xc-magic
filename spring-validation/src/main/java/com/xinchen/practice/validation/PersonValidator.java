package com.xinchen.practice.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author xinchen
 * @version 1.0
 * @date 15/03/2019 13:16
 */
public class PersonValidator implements Validator {

    /**
     * Can this Validator validate instances of the supplied Class?
     *
     * @param clazz class
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    /**
     * validates the given object and in case of validation errors, registers those with the given Errors object
     *
     * @param o      object
     * @param errors Errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Person p = (Person) o;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "negative value");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "too.darn.old");
        }
    }
}
