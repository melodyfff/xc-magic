package com.xinchen.spring.integration.remoting.webservice.server;

import com.xinchen.spring.integration.remoting.prototype.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 *
 * 兼容JAX-WS的AccountService实现，只需委托到根Web应用程序上下文中的AccountService实现。
 *
 * 此包装器类是必需的，因为JAX-WS需要使用专用的端点类,如果需要对外暴露服务
 *
 * Spring为JAX-WS servlet端点实现提供了一个方便的基类 {@link SpringBeanAutowiringSupport},这是在服务器端JAX-WS实现中注册的类
 *
 * 对于Java EE服务器，可以简单地将其定义为servlet,在web.xml中，服务器检测到这是一个JAX-WS端点并做出反应,Servlet名称通常需要与指定的WS服务名称匹配.
 *
 * {@link WebService} 标示这是一个webservice类
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 21/09/2019 14:08
 */
@WebService(serviceName = "AccountService")
@Component
public class AccountServiceEndpoint extends SpringBeanAutowiringSupport{

    /** 获取root web applicationContext中的 AccountService **/
    private WebServiceAccountService webServiceAccountService;

    public AccountServiceEndpoint(){}


    @Autowired(required = false)
    public AccountServiceEndpoint(WebServiceAccountService webServiceAccountService) {
        this.webServiceAccountService = webServiceAccountService;
    }

    @WebMethod
    public void insertAccount(Account account){
        webServiceAccountService.insertAccount(account);
    }

    @WebMethod
    public List<Account> getAccounts(String name){
        return webServiceAccountService.getAccounts(name);
    }
}
