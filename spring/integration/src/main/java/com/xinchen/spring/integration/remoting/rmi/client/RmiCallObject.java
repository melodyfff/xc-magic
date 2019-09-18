package com.xinchen.spring.integration.remoting.rmi.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 16:33
 */
@Slf4j
public class RmiCallObject {

    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public String call() {
        // additional methods using the accountService
        log.warn(((Proxy) accountService).toString());
        accountService.insertAccount(null);
        log.info("Result: {}",accountService.getAccounts("hello"));
        return this + " -> " + accountService.toString();
    }
}
