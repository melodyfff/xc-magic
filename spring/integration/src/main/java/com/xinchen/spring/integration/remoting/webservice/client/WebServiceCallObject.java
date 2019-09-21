package com.xinchen.spring.integration.remoting.webservice.client;

import com.xinchen.spring.integration.remoting.webservice.server.WebServiceAccountService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 *
 * @author xinchen
 * @version 1.0
 * @date 21/09/2019 15:45
 */
@Slf4j
public class WebServiceCallObject {

    private WebServiceAccountService webServiceAccountService;

    public WebServiceCallObject(WebServiceAccountService accountService) {
        this.webServiceAccountService = accountService;
    }

    public String call(){
        // additional methods using the accountService
        log.warn(((Proxy) webServiceAccountService).toString());
        webServiceAccountService.insertAccount(null);
        log.info("Result: {}",webServiceAccountService.getAccounts("hello"));
        return this + " -> " + webServiceAccountService.toString();
    }

}
