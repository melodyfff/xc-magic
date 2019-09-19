package com.xinchen.spring.integration.remoting.hessian;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import com.xinchen.spring.integration.remoting.prototype.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
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

    @Bean("/AccountService")
    public HessianServiceExporter hessianServiceExporter(){
        final HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(accountService());
        hessianServiceExporter.setServiceInterface(AccountService.class);
        return hessianServiceExporter;
    }
}
