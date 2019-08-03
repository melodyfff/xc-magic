package com.xinchen.base.config.auto;

import org.springframework.context.annotation.Bean;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/8/3 22:08
 */
public class MyConfig {
    @Bean
    public Hello hello(){
        return new Hello();
    }
}
