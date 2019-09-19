package com.xinchen.spring.integration.remoting.hessian.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * Hessian 客户端调用对象
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/19 22:39
 */
@Slf4j
public class HessianCallObject {

    private AccountService accountService;

    public HessianCallObject(AccountService accountService) {
        this.accountService = accountService;
    }

    public String call(){
        // additional methods using the accountService
        log.warn(((Proxy) accountService).toString());
        accountService.insertAccount(null);
        log.info("Result: {}",accountService.getAccounts("hello"));
        return this + " -> " + accountService.toString();
    }
}
