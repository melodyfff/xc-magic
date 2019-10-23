package com.xinchen.event.custom.event;

import java.util.EventObject;

/**
 *
 * 基于jdk封装事件源
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 14:58
 */
public class AppEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    /** 记录事件发生时候的系统时间 */
    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AppEvent(Object source) {
        // 初始化事件
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 返回时间发生时候的系统时间
     */
    public final long getTimestamp() {
        return timestamp;
    }
}
