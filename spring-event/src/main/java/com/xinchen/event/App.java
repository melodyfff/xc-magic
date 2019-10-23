package com.xinchen.event;

import com.xinchen.event.config.RootConfig;
import com.xinchen.event.spring.HelloEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        log.info(context.getDisplayName());


        context.publishEvent(new HelloEvent(App.class,"hello"));
    }
}
