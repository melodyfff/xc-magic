package com.xinchen.spring.proxying.api.metadata;

/**
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 12:26
 */
public class PersonImpl implements Person {

    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void action() {
        System.out.println("[PersonImpl] -> action");
    }
}
