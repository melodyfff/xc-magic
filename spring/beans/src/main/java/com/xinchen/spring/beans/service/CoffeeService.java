package com.xinchen.spring.beans.service;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/1/11 23:28
 */
public class CoffeeService {
    private static CoffeeService coffeeService = new CoffeeService();


    public static CoffeeService createInstance(){
        return coffeeService;
    }
}
