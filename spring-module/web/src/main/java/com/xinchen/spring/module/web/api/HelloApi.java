package com.xinchen.spring.module.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 22:14
 */
@RestController
public class HelloApi {
    @GetMapping("/")
    public String hello(){
        return "ok";
    }
}
