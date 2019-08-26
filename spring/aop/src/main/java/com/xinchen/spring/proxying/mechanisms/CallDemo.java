package com.xinchen.spring.proxying.mechanisms;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author xinchen
 * @version 1.0
 * @date 26/08/2019 11:08
 */
public class CallDemo {
    public static void main(String[] args) {
        Pojo pojo = new SimplePojo();
        // this is a direct method call on the 'pojo' reference
        // 直接调用对象引用 com.xinchen.spring.proxying.mechanisms.SimplePojo@2f333739
        pojo.foo();


        ProxyFactory factory = new ProxyFactory(pojo);
        factory.addInterface(Pojo.class);
        Pojo proxyPojo = (Pojo) factory.getProxy();
        // this is a method call on the proxy!
        // 默认使用jdk动态代理 org.springframework.aop.framework.JdkDynamicAopProxy@1e6111c1
        // public final class com.sun.proxy.$Proxy0
        proxyPojo.foo();
    }
}
