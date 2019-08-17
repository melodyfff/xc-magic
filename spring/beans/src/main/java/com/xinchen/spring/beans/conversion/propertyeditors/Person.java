package com.xinchen.spring.beans.conversion.propertyeditors;

/**
 * @author xinchen
 * @version 1.0
 * @date 17/08/2019 15:28
 */
public class Person {
    private String name;
    private PersonType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }
}
