package com.xinchen.spring.api;

/**
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 11:17
 */
public class LockedException extends Exception{
    public LockedException() {
        super();
    }

    public LockedException(String message) {
        super(message);
    }

    public LockedException(String message, Throwable cause) {
        super(message, cause);
    }
}
