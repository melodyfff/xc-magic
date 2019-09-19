package com.xinchen.spring.integration.remoting.hessian.server;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import com.xinchen.spring.integration.remoting.prototype.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 *
 * Hessian 服务端配置
 * Hessian是一个轻量级的RPC框架，基于HTTP协议传输
 * 使用Hessian二进制序列化，对于数据包比较大的情况比较友好
 * 对于复杂类型可能无法序列化，考虑采用其他
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 19/09/2019 17:32
 */
@Configuration
public class HessianServerConfig {
    @Bean("hessianAccountService")
    public AccountService accountService(){
        return new AccountServiceImpl();
    }

    @Bean("/hessian/AccountService")
    public HessianServiceExporter hessianAccountServiceExporter(){
        // 基于 BeanNameUrlHandlerMapping
        final HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(accountService());
        hessianServiceExporter.setServiceInterface(AccountService.class);
        return hessianServiceExporter;
    }
}
