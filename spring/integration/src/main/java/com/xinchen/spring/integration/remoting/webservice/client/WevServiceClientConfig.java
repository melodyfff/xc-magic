package com.xinchen.spring.integration.remoting.webservice.client;

import com.xinchen.spring.integration.remoting.webservice.server.WebServiceAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * JAX-WS 客户端配置
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 21/09/2019 15:28
 */
@Configuration
public class WevServiceClientConfig {

    @Bean
    @Lazy
    public WebServiceCallObject webServiceCallObject() throws MalformedURLException {
        return new WebServiceCallObject((WebServiceAccountService) accountWebService().getObject());
    }

    @Bean
    @Lazy
    public JaxWsPortProxyFactoryBean accountWebService() throws MalformedURLException {
        final JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean = new JaxWsPortProxyFactoryBean();

        jaxWsPortProxyFactoryBean.setServiceInterface(WebServiceAccountService.class);

        // http://localhost:8081/webservice/AccountService?WSDL 和 http://localhost:8081/webservice/AccountServiceEndpoint?WSDL 都能匹配上
        // http://localhost:8081/webservice/AccountServic??WSDL 不能匹配上
        // 匹配规则应该是startWith(),最好不要有干扰的同名start,不然去找对应的方法的时候可能找不到
        jaxWsPortProxyFactoryBean.setWsdlDocumentUrl(new URL("http://localhost:8081/webservice/AccountServiceEndpoint?WSDL"));

        // 对应于.wsdl文件中的targetNamespace
        jaxWsPortProxyFactoryBean.setNamespaceUri("http://server.webservice.remoting.integration.spring.xinchen.com/");
        // 对应于.wsdl文件中的服务名称
        jaxWsPortProxyFactoryBean.setServiceName("AccountService");
        // 对应于.wsdl文件中的端口名称
        jaxWsPortProxyFactoryBean.setPortName("AccountServiceEndpointPort");

        return jaxWsPortProxyFactoryBean;

    }
}
