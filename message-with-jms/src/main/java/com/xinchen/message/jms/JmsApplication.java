package com.xinchen.message.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**
 * EnableJms 触发发现带注释的方法 @JmsListener
 * {@link DefaultJmsListenerContainerFactoryConfigurer} 为Spring Boot提供的基础结构
 * {@link ConnectionFactory} 和 {@link JmsTemplate} 也为Spring Boot自动创建 ，默认pubSubDomain为false
 */
@SpringBootApplication
@EnableJms
public class JmsApplication {

    private static final Logger log = LoggerFactory.getLogger(JmsApplication.class);

    @Bean
    public JmsListenerContainerFactory<?> myJmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                      DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        // 这为此工厂提供了所有引导的默认值，包括消息转换器
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory,connectionFactory);

        // 如有必要，您仍然可以覆盖Boot的某些默认值。
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    /**
     *  Serialize message content to json using TextMessage
     * @return MessageConverter
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JmsApplication.class, args);

        final JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);


        log.info("Sending an email message.");

        jmsTemplate.convertAndSend("mailbox",new Email("info@example.com","Hello"));


    }

}
