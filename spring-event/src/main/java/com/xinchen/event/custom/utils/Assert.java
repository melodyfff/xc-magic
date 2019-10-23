package com.xinchen.event.custom.utils;

/**
 *
 * fast-fail
 *
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 15:57
 */
public abstract class Assert {
    public static void notNull(Object object,String message){
        if (null == object){
            throw new IllegalArgumentException(message);
        }
    }
}
