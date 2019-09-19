package com.xinchen.spring.integration.remoting.rmi.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;


/**
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 16:34
 */
@Configuration
public class RmiClientConfig {
    @Bean()
    @Lazy
    public RmiCallObject rmiCallObject(){
        final RmiCallObject rmiCallObject = new RmiCallObject();
        rmiCallObject.setAccountService((AccountService) rmiAccountService().getObject());
        return rmiCallObject;
    }

    @Bean
    @Lazy
    public RmiProxyFactoryBean rmiAccountService(){
        final RmiProxyFactoryBean rmiService = new RmiProxyFactoryBean();
        // 注： 这里是基于RMI的
        rmiService.setServiceUrl("rmi://localhost:1199/AccountService");
        rmiService.setServiceInterface(AccountService.class);
        return rmiService;
    }

}
