package com.xinchen.spring.integration.remoting.rmi.route;

import com.xinchen.spring.integration.remoting.rmi.client.RmiCallObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * RMI调用路由,{@link Lazy} 是因为部署在同一应用中,先后加载顺序会影响启动
 *
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 16:45
 */
@Api(
        value = "RMI",
        tags = "RMI调用",
        produces = "application/json",
        protocols = "http"
)
@RestController
public class RmiRoutController {

    @Resource
    @Lazy
    private RmiCallObject rmiCallObject;


    @ApiOperation(value = "触发RMI调用")
    @GetMapping("/rmi")
    public String call(){
        return rmiCallObject.call();
    }

}
