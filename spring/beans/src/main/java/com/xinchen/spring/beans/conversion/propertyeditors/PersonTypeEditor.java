package com.xinchen.spring.beans.conversion.propertyeditors;


import java.beans.PropertyEditorSupport;

/**
 * @author xinchen
 * @version 1.0
 * @date 17/08/2019 15:41
 */
public class PersonTypeEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 转换为大写
        setValue(new PersonType("admin".toUpperCase()));
    }
}
