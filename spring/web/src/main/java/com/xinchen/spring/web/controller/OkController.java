package com.xinchen.spring.web.controller;

import com.xinchen.spring.web.common.ApiResult;
import com.xinchen.spring.web.common.ResultCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 用于测试返回不同类型xml\json\jsp的数据
 *
 * 以下注释掉的写法都通用
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/14 0:34
 */
@RestController
public class OkController {

//    @RequestMapping("/ok")
//    public ModelAndView ok(){
//        ModelAndView mac = new ModelAndView();
//        mac.setViewName("ok");
//        mac.addObject("ok");
//        return mac;
//    }

//    @RequestMapping("/ok")
//    public ResponseEntity<String> ok(){
//        return ResponseEntity.ok("ok");
//
//    }

    @RequestMapping(path = "/ok",produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResult ok(){
        return ApiResult.of(ResultCode.SUCCESS);
    }

}
