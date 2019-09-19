package com.xinchen.spring.integration.remoting.hessian.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 *
 * Hessian 客户端调用配置
 *
 * {@link Lazy} 是因为客户端和服务端都部署在同一应用中，有先后顺序的加载问题
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/19 22:40
 */
@Configuration
public class HessianClientConfig {
    @Bean
    @Lazy
    public HessianCallObject hessianCallObject(){
        return new HessianCallObject((AccountService) hessianService().getObject());
    }

    @Bean
    @Lazy
    public HessianProxyFactoryBean hessianService(){
        final HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        // 注： 这里是基于http的
        // 可以增加安全性https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/integration.html#remoting-caucho-protocols
        hessianProxyFactoryBean.setServiceUrl("http://localhost:8080/hessian/AccountService");
        hessianProxyFactoryBean.setServiceInterface(AccountService.class);
        return hessianProxyFactoryBean;
    }
}
