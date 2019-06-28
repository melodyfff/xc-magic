package com.xinchen.ldap.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinchen
 * @version 1.0
 * @date 28/06/2019 13:01
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "Welcome to the home page.";
    }

}
