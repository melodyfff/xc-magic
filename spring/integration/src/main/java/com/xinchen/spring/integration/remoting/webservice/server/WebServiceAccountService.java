package com.xinchen.spring.integration.remoting.webservice.server;

import com.xinchen.spring.integration.remoting.prototype.Account;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * 因为JAX-WS要求端点接口和实现类使用@WebService，@SOAPBinding等等注释进行注释。
 *
 * 这意味着您不能（轻松地）将纯Java接口和实现类用作JAX-WS端点工件。您需要先对其进行相应注释。
 *
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 14:48
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface WebServiceAccountService {
    void insertAccount(Account account);
    List<Account> getAccounts(String name);
}
