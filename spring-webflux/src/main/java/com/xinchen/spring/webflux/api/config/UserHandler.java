package com.xinchen.spring.webflux.api.config;

import com.xinchen.spring.webflux.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * 具体处理逻辑
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/27 23:29
 */
@Component
public class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return ServerResponse
                .status(HttpStatus.OK)
                .body(Mono.just(new User(request.pathVariable("user"))),User.class);
    }

    public Mono<ServerResponse> getId(ServerRequest request) {
        Flux<User> user = Flux.just(new User(request.pathVariable("id")));
        return ServerResponse.ok().body(user, User.class);
    }
}
