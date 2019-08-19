package com.xinchen.spring.beans.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 19/08/2019 16:04
 */
public class FieldValueTestBean {
    @Value("#{systemProperties['user.language']}")
    private String defaultLocal;

    public String getDefaultLocal() {
        return defaultLocal;
    }

    public void setDefaultLocal(String defaultLocal) {
        this.defaultLocal = defaultLocal;
    }
}
