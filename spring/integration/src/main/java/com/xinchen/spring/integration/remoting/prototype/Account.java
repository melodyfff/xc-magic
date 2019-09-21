package com.xinchen.spring.integration.remoting.prototype;

import java.io.Serializable;

/**
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 14:47
 */
public class Account implements Serializable {

    private String name;

    public Account() {}

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                '}';
    }
}
