package com.xinchen.event.custom.message.event;

import com.xinchen.event.custom.event.AppEvent;
import com.xinchen.event.custom.message.Message;

/**
 *
 * 作为{@link Message}引发事件的基类
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:06
 */
public abstract class MessageEvent extends AppEvent {

    /**
     * 创建一个 消息事件
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MessageEvent(Message source) {
        super(source);
    }

    /**
     * 返回事件触发的源头
     */
    public final Message getMessage(){
        return (Message) getSource();
    }
}
