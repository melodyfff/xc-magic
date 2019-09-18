package com.xinchen.spring.integration.remoting.prototype;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 14:50
 */
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Override
    public void insertAccount(Account account) {
        // do something
        log.info(this+" -> insertAccount() be called.");
    }

    @Override
    public List<Account> getAccounts(String name) {
        // do something
        log.info(this+" -> getAccounts() be called.");
        return Collections.singletonList(new Account(name));
    }
}
