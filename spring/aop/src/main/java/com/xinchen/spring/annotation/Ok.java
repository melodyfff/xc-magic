package com.xinchen.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * <pre>
 * 1. 只对public生效
 * 2. 内部调用直接调用,由于使用的是未被spring代理后的代理类，也不会生效
 * </pre>
 *
 * @author xinchen
 * @version 1.0
 * @date 23/08/2019 15:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Ok {
    String value() default "";
}
