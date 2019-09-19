package com.xinchen.spring.integration.remoting.httpinvoker.server;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import com.xinchen.spring.integration.remoting.prototype.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

/**
 *
 * Spring Http Invoker 服务端配置
 *
 * 参考： https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/integration.html#remoting-httpinvoker
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/19 23:23
 */
@Configuration
public class HttpInvokerServerConfig {

    @Bean
    public AccountService httpInvokerAccountService(){
        return new AccountServiceImpl();
    }

    @Bean("/httpinvoker/AccountService")
    public HttpInvokerServiceExporter httpInvokerAccountServiceExporter(){
        // 基于 BeanNameUrlHandlerMapping
        final HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(httpInvokerAccountService());
        httpInvokerServiceExporter.setServiceInterface(AccountService.class);
        return httpInvokerServiceExporter;
    }
}
