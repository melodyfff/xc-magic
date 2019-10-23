package com.xinchen.event.custom.message.event;

import com.xinchen.event.custom.message.Message;

/**
 *
 * 当{@link Message}接收到消息时触发
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:37
 */
public class ReceiveMessageEvent extends MessageEvent{

    /**
     * 创建一个 消息事件
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ReceiveMessageEvent(Message source) {
        super(source);
    }
}
