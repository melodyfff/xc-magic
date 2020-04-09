package com.xinchen.spring.module.web.core.service;


/**
 *
 * 业务接口
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 20:10
 */
public interface BusiService<T,R> {
    R applay(T t);
}
