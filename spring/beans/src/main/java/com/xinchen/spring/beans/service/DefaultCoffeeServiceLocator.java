package com.xinchen.spring.beans.service;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/1/11 23:35
 */
public class DefaultCoffeeServiceLocator {

    private static CoffeeService coffeeService = new CoffeeService();

    public CoffeeService createCoffeeServiceInstance(){
        return coffeeService;
    }
}
