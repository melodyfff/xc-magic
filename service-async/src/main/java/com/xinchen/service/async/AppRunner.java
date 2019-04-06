package com.xinchen.service.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * {@link CommandLineRunner}
 * 如果您需要在启动后运行某些特定代码SpringApplication，则可以实现ApplicationRunner或CommandLineRunner接口。
 * 两个接口以相同的方式工作并提供单个run方法，该方法在SpringApplication.run(…​)完成之前调用
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 20:22
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookUpService gitHubLookUpService;

    public AppRunner(GitHubLookUpService gitHubLookUpService) {
        this.gitHubLookUpService = gitHubLookUpService;
    }

    @Override
    public void run(String... args) throws Exception {
        // start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = gitHubLookUpService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookUpService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookUpService.findUser("Spring-Projects");

        // wait until they are all done
        CompletableFuture.allOf(page1, page2, page3).join();

        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }
}
