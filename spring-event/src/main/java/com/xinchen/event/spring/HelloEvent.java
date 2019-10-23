package com.xinchen.event.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 14:56
 */
public class HelloEvent extends ApplicationEvent {

    private String name;

    public HelloEvent(Object source,String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "HelloEvent{" +
                "name='" + name + '\'' +
                ", source=" + source +
                '}';
    }
}
