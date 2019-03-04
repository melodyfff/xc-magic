package com.xinchen.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/1 0:12
 */
@Controller
public class HelloController {
    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

}
