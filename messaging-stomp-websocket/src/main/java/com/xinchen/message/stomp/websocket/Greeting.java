package com.xinchen.message.stomp.websocket;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 22:50
 */
public class Greeting {

    private String content;

    public Greeting(){}


    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
