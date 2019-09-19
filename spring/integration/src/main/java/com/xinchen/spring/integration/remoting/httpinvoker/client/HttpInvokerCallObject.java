package com.xinchen.spring.integration.remoting.httpinvoker.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/19 23:29
 */
@Slf4j
public class HttpInvokerCallObject {

    private AccountService accountService;

    public HttpInvokerCallObject(AccountService accountService) {
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
