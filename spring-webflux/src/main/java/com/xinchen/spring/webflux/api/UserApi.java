package com.xinchen.spring.webflux.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinchen.spring.webflux.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 *
 * Spring WebFlux 基于注解
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/27 23:18
 */
@RestController
@RequestMapping("/v1/users")
public class UserApi {

    /**
     * curl http://localhost:8080/v1/users/us?token=123
     *
     * @param user user
     * @return User     Mono非阻塞
     */
    @GetMapping("/{user}")
    public Mono<User> getUser(@PathVariable String user) {
        return Mono.just(new User(user));
    }

    /**
     * curl http://localhost:8080/v1/users/user/1?token=123
     * @param id id
     * @return User    Flux非阻塞
     */
    @GetMapping("/user/{id}")
    public Flux<User> getId(@PathVariable String id){
        return Flux.just(new User(id));
    }


}
