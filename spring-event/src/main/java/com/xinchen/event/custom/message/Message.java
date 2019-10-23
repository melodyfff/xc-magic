package com.xinchen.event.custom.message;

import com.xinchen.event.custom.publisher.MessageEventPublisher;

/**
 *
 * 消息服务
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:08
 */
public interface Message extends MessageEventPublisher {

    /**
     * 消息事件的ID
     */
    String getId();

    /**
     * 发送消息动作
     */
    boolean send(String message);

    /**
     * 接收消息动作
     */
    boolean receive(String message);

}
