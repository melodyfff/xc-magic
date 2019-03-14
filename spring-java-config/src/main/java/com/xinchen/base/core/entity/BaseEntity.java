package com.xinchen.base.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 解决 No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer
 * jpa在转换的时候会带有属性 hibernateLazyInitializer handler
 * @author xinchen
 * @version 1.0
 * @date 04/03/2019 12:56
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntity {
}
