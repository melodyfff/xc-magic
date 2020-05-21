package com.xinchen.spring.web.controller.config;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * 采用编码配置路由的方式去注册RequestMappingHandlerMapping
 *
 * 官网文档相关： https://docs.spring.io/spring/docs/5.2.6.RELEASE/spring-framework-reference/web.html#mvc-ann-requestmapping-registration
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/5/7 23:32
 */
@Component
public class UserHandler {
    public ResponseEntity<String> ok(@RequestBody String say){
        return ResponseEntity.ok(say);
    }
}
