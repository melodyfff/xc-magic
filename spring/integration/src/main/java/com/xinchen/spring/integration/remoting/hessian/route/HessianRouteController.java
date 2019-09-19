package com.xinchen.spring.integration.remoting.hessian.route;

import com.xinchen.spring.integration.remoting.hessian.client.HessianCallObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/19 22:47
 */
@Api(
        value = "Hessian",
        tags = "Hessian调用",
        produces = "application/json",
        protocols = "http"
)
@RestController
public class HessianRouteController {

    @Resource
    @Lazy
    private HessianCallObject hessianCallObject;

    @ApiOperation(value = "触发Hessian调用")
    @GetMapping("/hessian")
    public String call(){
        return hessianCallObject.call();
    }

}
