package com.xinchen.message.stomp.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * {@link MessageMapping} 注解确保如果message发送到 '/hello',  则greeting()会被调用
 *
 * {@link SendTo}
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 22:51
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        // 模拟延迟
        // 这是为了演示客户端发送消息后，只要需要异步处理消息，服务器就可以使用。
        // 客户可以继续进行它需要做的任何工作而无需等待响应
        Thread.sleep(1000L);

        // 返回值被广播给所有订阅者
        return new Greeting(String.format("Hello, %s !",HtmlUtils.htmlEscape(message.getName(), "UTF-8")));
    }
}
