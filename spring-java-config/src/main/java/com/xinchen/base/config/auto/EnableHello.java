package com.xinchen.base.config.auto;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 参考： https://blog.csdn.net/mapleleafforest/article/details/87903718
 *
 * 这里的{@link org.springframework.context.annotation.Import}支持：
 *
 * {@link Configuration}, {@link ImportSelector}, {@link ImportBeanDefinitionRegistrar} or regular component classes to import
 *
 * 所以自定义Enable*很灵活
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/8/3 21:50
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({HelloImportSelector.class})
public @interface EnableHello {
}
