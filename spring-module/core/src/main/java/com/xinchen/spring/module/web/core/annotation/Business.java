package com.xinchen.spring.module.web.core.annotation;

import com.xinchen.spring.module.web.core.protocol.ServiceType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 20:21
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Business {
    /**
     * @see ServiceType
     * @return ServiceType
     */
    ServiceType serviceType();
}
