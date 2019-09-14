package com.xinchen.spring.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 接收默认的{@link DefaultHandlerExceptionResolver},解决Servlet相关异常信息不打印问题
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/14 16:14
 */
@Slf4j
public class MyDefaultHandlerExceptionResolver extends DefaultHandlerExceptionResolver {
    public MyDefaultHandlerExceptionResolver() {
        setOrder(1);
        setWarnLogCategory(getClass().getName());
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // 仅仅打印异常信息，当然也可以对特殊的异常进行处理
        // 比如这里可以强制转换到我们自己的异常处理页面
        // 这里如果前置条件是： 关闭了了<mvc:default-servlet-handler/> 以及servlet.setThrowExceptionIfNoHandlerFound(true)
        if (null != ex){
            log.error("MyDefaultHandlerExceptionResolver ERROR: ",ex);
            return new ModelAndView("error");
        }

        // 继续走原异常处理逻辑
        return super.doResolveException(request, response, handler, ex);
    }

}
