package com.xinchen.spring.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义全局异常处理,
 *
 * {@link ControllerAdvice}无法处理Filter中抛出的异常
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/13 23:02
 */
@RestControllerAdvice(basePackages = "com.xinchen.spring.web")
@Slf4j
public class GlobalExceptionResolver {


    @ExceptionHandler(Exception.class)
    public Object throwableHandler(HttpServletRequest request, HttpServletResponse response,Throwable ex){
        log.error("ERROR: ",ex);
        return ex.getMessage();
    }

}
