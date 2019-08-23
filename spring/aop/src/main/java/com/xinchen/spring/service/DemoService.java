package com.xinchen.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author xinchen
 * @version 1.0
 * @date 20/08/2019 15:26
 */
@Service
public class DemoService {

    static final String SAY = "Hello World!";

    public String say(){
        System.out.println(SAY);
        return SAY;
    }

    public void say(String world){
        System.out.println("Hello "+ world);
    }

    public void say(String p1,String p2){
        System.out.println(p1+p2);
    }

}
