package com.xinchen.message.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 19:27
 */
@Component
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);
    /**
     * 消息队列监听器
     * destination 队列地址
     * containerFactory 监听器容器工厂, 若存在2个以上的监听容器工厂,需进行指定
     */
    @JmsListener(destination = "mailbox",containerFactory = "myJmsListenerContainerFactory")
    public void receiveMessage(Email email){
        log.info("Received <{}>",email);
    }

}
