package com.xinchen.spring.integration.remoting.prototype;

import java.util.List;

/**
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 14:48
 */
public interface AccountService {

    void insertAccount(Account account);

    List<Account> getAccounts(String name);
}
