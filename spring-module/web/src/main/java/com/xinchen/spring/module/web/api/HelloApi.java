package com.xinchen.spring.module.web.api;

import com.xinchen.spring.module.web.BusiServiceImpl;
import com.xinchen.spring.module.web.core.service.BusiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 22:14
 */
@RestController
public class HelloApi {

    @Resource
    @Qualifier("BusiServiceImpl")
    @Lazy
    private BusiService busiService;

    @GetMapping("/")
    public String hello(){
        return (String) busiService.applay("ok");
    }
}
