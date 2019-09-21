package com.xinchen.spring.integration.remoting.webservice.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

/**
 * JAX-WS 服务端配置
 *
 * @author xinchen
 * @version 1.0
 * @date 21/09/2019 14:54
 */
@Configuration
public class WebServiceServerConfig {

    @Bean(name = "webServiceAccountService")
    public WebServiceAccountService webServiceAccountService(){
        return new WebServiceAccountServiceImpl();
    }

    @Bean
    public SimpleJaxWsServiceExporter simpleJaxWsServiceExporter(){
        final SimpleJaxWsServiceExporter simpleJaxWsServiceExporter = new SimpleJaxWsServiceExporter();

        // DEFAULT_BASE_ADDRESS = "http://localhost:8080/"
        simpleJaxWsServiceExporter.setBaseAddress("http://localhost:8081/webservice/");

        return simpleJaxWsServiceExporter;
    }

}
