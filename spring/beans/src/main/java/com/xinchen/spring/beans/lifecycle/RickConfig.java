package com.xinchen.spring.beans.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/24 23:34
 */
@Configuration
public class RickConfig {

    /**
     * 指定自定义初始化方法和自定义销毁方法
     * @return Rick
     */
    @Bean(initMethod = "customInit",destroyMethod = "customDestroy")
    public Rick rick(){
        return new Rick();
    }

    @Bean
    public RickBeanPostProcess rickBeanPostProcess(){
        return new RickBeanPostProcess();
    }
}
