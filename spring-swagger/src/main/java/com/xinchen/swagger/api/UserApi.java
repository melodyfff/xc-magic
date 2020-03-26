package com.xinchen.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 请求地址
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/26 23:23
 */
@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户相关接口",value = "User API接口")
public class UserApi {

    @ApiOperation(value = "get请求调用示例", notes = "get说明", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok")
    })
    @GetMapping("/login")
    public ResponseEntity<Object> login(){
        return ResponseEntity.ok("ok");
    }
}
