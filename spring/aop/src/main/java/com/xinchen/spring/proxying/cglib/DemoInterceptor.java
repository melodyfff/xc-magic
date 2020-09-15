package com.xinchen.spring.proxying.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * cglib方式的方法拦截增强
 *
 * see {@link Enhancer#setCallback(org.springframework.cglib.proxy.Callback)}
 * see {@link Enhancer#create()}
 * see {@link MethodInterceptor}
 * see {@link Callback}
 * @author xinchen
 * @version 1.0
 * @date 15/09/2020 14:10
 */
public class DemoInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // o cglib根据字节码生成的增强对象 com.xinchen.spring.service.DemoService$$EnhancerByCGLIB$$97eaf8eb@57fffcd7
        // Method 要执行的方法信息
        // args 请求参数
        // methodProxy 方法代理，包含


        // do some before
        final Object object = methodProxy.invokeSuper(o, args);
        // do some after


        return object;
    }
}
