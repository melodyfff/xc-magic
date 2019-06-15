package com.xinchen.base.config;

import com.xinchen.base.core.vo.MultipleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 *
 *
 * scope 采用 prototype,并使用 ScopedProxyMode.TARGET_CLASS 生成代理(uses CGLIB)
 * 每次获取该对象的bean时,都是获取的最新的
 *
 * @author xinchen
 * @version 1.0
 * @date 12/06/2019 20:40
 */
@Configuration
public class MultipleBeanConfig {
    @Bean(name = "multipleBean")
    @Scope(scopeName = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MultipleBean multipleBean(){
        return new MultipleBean();
    }
}
