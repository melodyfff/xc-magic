package com.xinchen.spring.beans.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/25 23:02
 */
@Configuration
@ComponentScan("com.xinchen.spring.beans.scope")
public class RickConfig {

    /**
     * 配置scope为prototype,代理类为cglib
     * @return Rick
     */
    @Bean(name = "rick")
    @Scope(scopeName = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Rick rick(){
        return new Rick();
    }
}
