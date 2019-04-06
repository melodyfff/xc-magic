package com.xinchen.service.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 *
 * Spring Boot自动提供RestTemplateBuilder使用任何自动配置位（即MessageConverter）自定义默认值
 *
 * {@link Async} 表示它将在单独的线程上运行
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 20:10
 */
@Service
public class GitHubLookUpService {

    private static final Logger log = LoggerFactory.getLogger(GitHubLookUpService.class);

    private final RestTemplate restTemplate;

    public GitHubLookUpService(RestTemplateBuilder restTemplateBuilder ) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        log.info("Looking up" + user);
        String url = String.format("https://api.github.com/users/%s", user);

        final User results = restTemplate.getForObject(url, User.class);

        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);

        return CompletableFuture.completedFuture(results);
    }
}
