package com.xinchen.spring.integration.remoting.webservice.server;

import com.xinchen.spring.integration.remoting.prototype.Account;
import lombok.extern.slf4j.Slf4j;

import javax.jws.WebService;
import java.util.Collections;
import java.util.List;

/**
 *
 * 因为JAX-WS要求端点接口和实现类使用@WebService，@SOAPBinding等等注释进行注释。
 *
 * 这意味着您不能（轻松地）将纯Java接口和实现类用作JAX-WS端点工件。您需要先对其进行相应注释。
 *
 * @author xinchen
 * @version 1.0
 * @date 21/09/2019 16:19
 */
@WebService(endpointInterface = "com.xinchen.spring.integration.remoting.webservice.server.WebServiceAccountService")
@Slf4j
public class WebServiceAccountServiceImpl implements WebServiceAccountService {

    public WebServiceAccountServiceImpl() {
    }
    @Override
    public void insertAccount(Account account) {
        // do something
        log.warn(this+" -> insertAccount() be called.");
    }
    @Override
    public List<Account> getAccounts(String name) {
        // do something
        log.warn(this+" -> getAccounts() be called.");
        return Collections.singletonList(new Account(name));
    }
}
