package com.xinchen.spring.integration.remoting.webservice.router;

import com.xinchen.spring.integration.remoting.webservice.client.WebServiceCallObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xinchen
 * @version 1.0
 * @date 21/09/2019 15:50
 */
@Api(
        value = "JAX-WS",
        tags = "JAX-WS调用",
        produces = "application/json",
        protocols = "http"
)
@RestController
public class WebServiceRouteController {
    @Resource
    @Lazy
    private WebServiceCallObject webServiceCallObject;

    @ApiOperation(value = "触发JAX-WS调用")
    @GetMapping("/webservice")
    public String call(){
        return webServiceCallObject.call();
    }

}
