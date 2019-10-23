package com.xinchen.event.custom.publisher;

import com.xinchen.event.custom.event.AppEvent;
import com.xinchen.event.custom.message.Message;
import com.xinchen.event.custom.message.event.ReceiveMessageEvent;
import com.xinchen.event.custom.message.event.SentMessageEvent;

/**
 *
 * 封装Message事件发布功能
 *
 * 用作{@link Message}的 super-interface
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:28
 */
@FunctionalInterface
public interface MessageEventPublisher {

    /**
     * 通知所有在此注册的listener
     *
     * @param event 要发布的事件
     * @see #publishEvent(Object)
     * @see ReceiveMessageEvent
     * @see SentMessageEvent
     *
     */
    default void publishEvent(AppEvent event){
        publishEvent(event);
    }

    /**
     * 通知所有在此注册的listener
     *
     * 针对需要执行很长时间并且可能造成阻塞的操作,鼓励事件监听器尽可能的使用异步来提高效率
     *
     * @param event 要发布的事件
     */
    void publishEvent(Object event);
}
