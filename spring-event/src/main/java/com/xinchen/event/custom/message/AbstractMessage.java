package com.xinchen.event.custom.message;

import com.xinchen.event.custom.event.AppEvent;
import com.xinchen.event.custom.message.event.MessageEvent;
import com.xinchen.event.custom.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:15
 */
public abstract class AbstractMessage implements Message{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 消息的唯一id */
    private String id = makeId(this);


    @Override
    public String getId() {
        // 返回消息的id
        return this.id;
    }

    @Override
    public void publishEvent(Object event) {
        publishEvent(event,null);
    }


    protected void publishEvent(Object event,String nonMeans){
        Assert.notNull(event,"Event must not be null");

        AppEvent appEvent;
        if (event instanceof MessageEvent){
            appEvent = (AppEvent) event;
        } else {
            // spring中还有一个PayloadApplicationEvent进行事件封装,暂时不处理
            logger.warn("not support yet.");
        }
    }

    private static String makeId(Object obj){
        // 模仿spring生成Unique id 为这条消息
        return obj == null ?
                "" :
                obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
    }
}
