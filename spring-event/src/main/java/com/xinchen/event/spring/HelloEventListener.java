package com.xinchen.event.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 17:04
 */
@Component
@Slf4j
public class HelloEventListener  {

    @EventListener
    public void onApplicationEvent(HelloEvent event) {
        log.info(this + " receive {} ", event);
    }
}
