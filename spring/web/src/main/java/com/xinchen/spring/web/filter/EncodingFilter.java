package com.xinchen.spring.web.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 统一编码格式
 *
 * xml中的格式是：
 *
 * <pre>
 *    <filter>
 *         <filter-name>encodingFilter</filter-name>
 *         <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
 *         <async-supported>true</async-supported>
 *         <init-param>
 *             <param-name>encoding</param-name>
 *             <param-value>UTF-8</param-value>
 *         </init-param>
 *     </filter>
 *     <filter-mapping>
 *         <filter-name>encodingFilter</filter-name>
 *         <url-pattern>/*</url-pattern>
 *     </filter-mapping>
 * </pre>
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/14 17:13
 */
@WebFilter(
        filterName = "encodingFilter",
        urlPatterns = "/*",
        asyncSupported = true,
        initParams = {@WebInitParam(name = "encoding",value = "UTF-8")}
)
public class EncodingFilter extends CharacterEncodingFilter {
}
