package com.xinchen.jwt.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 全局异常处理
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/5/29 0:14
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e){
        final JSONObject jsonObject = new JSONObject();
        String msg = e.getMessage();
        if (StringUtils.isEmpty(msg)){
            return "500";
        }
        jsonObject.put("message", msg);
        return jsonObject;
    }
}
