package com.xinchen.event.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 17:27
 */
@Component
@Slf4j
public class HelloEventListener2 implements ApplicationListener<HelloEvent> {

    @Override
    public void onApplicationEvent(HelloEvent event) {
        log.info(this + " receive {} ", event);
    }
}
