package com.xinchen.spring.integration.remoting.httpinvoker.route;

import com.xinchen.spring.integration.remoting.httpinvoker.client.HttpInvokerCallObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/20 0:15
 */
@Api(
        value = "HttpInvoker",
        tags = "HttpInvoker调用",
        produces = "application/json",
        protocols = "http"
)
@RestController
public class HttpInvokerRouteController {
    @Resource
    @Lazy
    private HttpInvokerCallObject httpInvokerCallObject;


    @ApiOperation(value = "触发HttpInvoker调用")
    @GetMapping("/httpinvoker")
    public String call(){
        return httpInvokerCallObject.call();
    }
}
