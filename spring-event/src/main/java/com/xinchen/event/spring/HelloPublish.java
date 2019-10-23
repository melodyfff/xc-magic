package com.xinchen.event.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 17:06
 */
@Component
public class HelloPublish {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(String message){
        publisher.publishEvent(new HelloEvent(this,message));
    }
}
