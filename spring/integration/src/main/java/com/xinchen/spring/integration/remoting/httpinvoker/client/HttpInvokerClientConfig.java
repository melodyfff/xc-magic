package com.xinchen.spring.integration.remoting.httpinvoker.client;

import com.xinchen.spring.integration.remoting.prototype.AccountService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.util.Collections;

/**
 * Spring Http Invoker 客户端配置
 *
 * Spring使用JDK或Apache提供的标准工具HttpComponents来执行HTTP调用。如果您需要更高级且更易于使用的功能，请使用后者。
 *
 * 有关更多信息，请参阅 http://hc.apache.org/httpcomponents-client-ga/
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/19 23:31
 */
@Configuration
public class HttpInvokerClientConfig {

    @Bean
    @Lazy
    public HttpInvokerCallObject httpInvokerCallObject1(){
        // 通过HttpInvokerProxyFactoryBean获取服务
        return new HttpInvokerCallObject((AccountService)accountServiceHttpInvokerProxyFactoryBean().getObject());
    }

    @Bean
    @Lazy
    public HttpInvokerProxyFactoryBean accountServiceHttpInvokerProxyFactoryBean(){
        // 通过HttpInvokerProxyFactoryBean获取服务
        final HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        // 使用apache的HttpComponents服务调用，默认是JDK的HTTP功能
        httpInvokerProxyFactoryBean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor());

        httpInvokerProxyFactoryBean.setServiceUrl("http://localhost:8080/httpinvoker/AccountService");
        httpInvokerProxyFactoryBean.setServiceInterface(AccountService.class);
        return httpInvokerProxyFactoryBean;
    }


    /**
     * 自定义http invoke进行invoke调用时的细节
     * @return HttpComponentsHttpInvokerRequestExecutor
     */
    @Bean
    @Lazy
    public HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor(){
        // 构建HttpClient
        final CloseableHttpClient client = HttpClientBuilder.create()
                // 连接池管理 ， 最大活动连接数，默认值为20 ，每个主机的最大并行数，默认为2
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                // 重试策略, 默认值： 请求重试3次，是否开启request重试
                .setRetryHandler(new StandardHttpRequestRetryHandler(3, false))
                // 保持长连接，需要在Header头添加
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                // 添加header头
                .setDefaultHeaders(Collections.singletonList(new BasicHeader("Connection", "keep-alive")))
                .build();


        final HttpComponentsHttpInvokerRequestExecutor executor = new HttpComponentsHttpInvokerRequestExecutor();
        // 读写超时,默认 60 * 1000 ms
        executor.setReadTimeout(60 * 1000);
        // 连接超时,默认60 * 1000 ms
        executor.setConnectTimeout(60 * 1000);
        // 连接不够用时的等待时间,ms
        executor.setConnectionRequestTimeout(500);
        executor.setHttpClient(client);
        return executor;
    }
}
