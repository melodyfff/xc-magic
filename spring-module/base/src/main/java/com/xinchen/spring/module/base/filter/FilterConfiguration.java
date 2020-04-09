package com.xinchen.spring.module.base.filter;

import org.springframework.context.annotation.Bean;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 22:12
 */
public class FilterConfiguration {
    @Bean
    public CustomerFilter customerFilter(){
        return new CustomerFilter();
    }

    @Bean
    public CustomerFilter2 customerFilter2(){
        return new CustomerFilter2();
    }
}
