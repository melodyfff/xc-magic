package com.xinchen.spring.proxying.mechanisms;

/**
 * @author xinchen
 * @version 1.0
 * @date 26/08/2019 11:07
 */
public class SimplePojo implements Pojo{

    @Override
    public void foo() {
        this.bar();
    }

    @Override
    public void bar() {
        // some logic
    }
}
