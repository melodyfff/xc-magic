package com.xinchen.event.custom.message.event;

import com.xinchen.event.custom.message.Message;

/**
 *
 * 当{@link Message}发送消息时触发事件
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:35
 */
public class SentMessageEvent extends MessageEvent{
    /**
     * 创建一个 消息事件
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SentMessageEvent(Message source) {
        super(source);
    }
}
