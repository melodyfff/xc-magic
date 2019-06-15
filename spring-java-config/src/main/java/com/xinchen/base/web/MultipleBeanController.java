package com.xinchen.base.web;

import com.xinchen.base.core.service.MultipleBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 *
 *
 * 访问/test 查看scope的不同
 * @author xinchen
 * @version 1.0
 * @date 12/06/2019 19:16
 */

@RestController
public class MultipleBeanController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private MultipleBeanService multipleBeanService;

    @GetMapping("/test")
    public String test(){
        multipleBeanService.test();
        return "hello";
    }
}
