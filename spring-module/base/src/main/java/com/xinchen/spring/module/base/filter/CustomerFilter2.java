package com.xinchen.spring.module.base.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 自定义拦截器，实现了{@link Ordered} 定义执行顺序
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 21:55
 */
@Slf4j
public class CustomerFilter2 extends OncePerRequestFilter implements Ordered {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info(">>>> into [CoustomFilter2]");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public int getOrder() {
        return -101;
    }
}
