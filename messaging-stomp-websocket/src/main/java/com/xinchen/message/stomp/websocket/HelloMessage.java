package com.xinchen.message.stomp.websocket;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 22:48
 */
public class HelloMessage {

    private String name;

    public HelloMessage(){

    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
