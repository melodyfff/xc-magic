package com.xinchen.service.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    public static void main(String[] args) {
        // close the application context to shut down the custom ExecutorService
        SpringApplication.run(AsyncApplication.class, args).close();
    }

    /**
     * 定义多线程处理
     * 如果用户没有定义Executor bean，Spring会创建一个SimpleAsyncTaskExecutor并使用它
     * @return {@link ThreadPoolTaskExecutor}
     */
    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }

}
