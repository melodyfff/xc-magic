package com.xinchen.spring.beans.spel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xinchen
 * @version 1.0
 * @date 19/08/2019 16:05
 */
@Configuration
public class SpelConfig {
    @Bean
    public FieldValueTestBean fieldValueTestBean(){
        return new FieldValueTestBean();
    }
}
