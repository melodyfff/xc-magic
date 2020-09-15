package com.xinchen.spring.proxying.cglib;

import com.xinchen.spring.service.DemoService;
import org.springframework.cglib.proxy.Enhancer;

/**
 *
 * spring 自己的cglib根据ASM字节码技术动态生成子类，无需提供接口
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 15/09/2020 13:18
 */
public class DemoCglibApi {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置父类，生成继承子类，需要注意，final的方法无法生成
        enhancer.setSuperclass(DemoService.class);
        // 增强方法调用拦截器
        enhancer.setCallback(new DemoInterceptor());

        // 创建代理子类
        final DemoService demoService = (DemoService) enhancer.create();

        // 使用
        demoService.say();
        demoService.say("ok");
        demoService.say("H","L");
    }
}
