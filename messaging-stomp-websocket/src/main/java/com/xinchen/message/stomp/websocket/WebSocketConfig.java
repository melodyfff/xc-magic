package com.xinchen.message.stomp.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 *
 * {@link EnableWebSocketMessageBroker} 支持WebSocket消息处理，由消息代理处理
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 23:00
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 注册 broker (可以理解为broker 帮你把消息从发送端传送到接收端)
        // 用一个简单的基于内存的消息代理，以在前缀为“/ topic”的目标上将问候消息传回客户端
        registry.enableSimpleBroker("/topic");

        // 为绑定了@MessageMapping注释方法的消息指定“/app”前缀
        // 此前缀将用于定义所有消息映射; 例如，“/app/hello”是GreetingController.greeting()方法映射到处理的端点。
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册端点 “/messaging-stomp-websocket”，启用SockJS后备选项，以便在WebSocket不可用时可以使用备用传输
        // SockJS客户端将尝试连接到“/messaging-stomp-websocket”并使用可用的最佳传输（websocket，xhr-streaming，xhr-polling等）
        registry.addEndpoint("messaging-stomp-websocket").withSockJS();
    }
}
