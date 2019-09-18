package com.xinchen.spring.integration.remoting.rmi.server;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import com.xinchen.spring.integration.remoting.prototype.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 *
 *
 * RMI (Remote Method Invocation) 服务端配置
 *
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 14:56
 */
@Configuration
public class RmiServerConfig {

    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }


    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        final RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        // 设置服务名
        rmiServiceExporter.setServiceName("AccountService");
        // 设置具体的服务
        rmiServiceExporter.setService(accountService());
        // 设置服务接口
        rmiServiceExporter.setServiceInterface(AccountService.class);
        // 设置暴露的端口,默认端口 1099
        // 服务绑定在 rmi://$HOST:1199/AccountService
        rmiServiceExporter.setRegistryPort(1199);
        return rmiServiceExporter;
    }

}
