package com.xinchen.spring.targetsource.detadata;

/**
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 10:40
 */
public class BusinessService {
    public void hello(){
        System.out.println(Thread.currentThread().getName()+" - "+this + " -> hello()");
    }
}
