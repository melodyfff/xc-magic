package com.xinchen.spring.webflux.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 *
 * Spring WebFlux 基于配置 把路由和具体请求逻辑分离开
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/27 23:27
 */
@Configuration
public class RoutingConfiguration {
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler){
        // curl http://localhost:8080/v2/users/us?token=123
        return route(GET("/v2/users/{user}"),userHandler::getUser)
                // curl http://localhost:8080/v2/users/user/1?token=123
                .andRoute(GET("/v2/users/user/{id}"),userHandler::getId);
    }
}
