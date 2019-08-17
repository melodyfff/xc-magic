package com.xinchen.spring.beans.conversion.propertyeditors;

/**
 * @author xinchen
 * @version 1.0
 * @date 17/08/2019 15:29
 */
public class PersonType {

    private String typeName;

    public PersonType(){}

    public PersonType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
