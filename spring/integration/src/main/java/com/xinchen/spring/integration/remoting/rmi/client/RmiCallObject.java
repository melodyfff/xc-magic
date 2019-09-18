package com.xinchen.spring.integration.remoting.rmi.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;

/**
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 16:33
 */
public class RmiCallObject {

    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void call(){
        // additional methods using the accountService
        accountService.insertAccount(null);
        accountService.getAccounts("hello");
    }
}
