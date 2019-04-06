package com.xinchen.message.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class RedisApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisApplication.class);


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 添加MessageListener,以及监听的频道
        // Topic(话题) 有 PatternTopic 和 ChannelTopic
        container.addMessageListener(listenerAdapter,new PatternTopic("chat"));

        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver){
        // MessageListenerAdapter 实现了 MessageListener
        // 设置处理的消息的service以及处理的方法（默认方法名为: handleMessage）
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver(CountDownLatch latch){
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch(){
        // 演示使用，保证全局只存在唯一的CountDownLatch
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }




    public static void main(String[] args) throws InterruptedException {
        final ApplicationContext context = SpringApplication.run(RedisApplication.class, args);

        final StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
        final CountDownLatch latch = context.getBean(CountDownLatch.class);

        LOGGER.info("Sending message...");
        template.convertAndSend("chat","Hello from Redis!");

        // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        // 也可使用 boolean await(long timeout, TimeUnit unit)设置超时时间以及判断是否执行完毕
        latch.await();

        // redis 控制台可通过输入： subscribe chat 进入频道查看
        System.exit(0);

    }

}
