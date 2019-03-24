package com.xinchen.web.server.mapping;

import com.xinchen.web.server.servlet.DefaultServlet;
import com.xinchen.web.server.servlet.FaviconServlet;

import java.util.ArrayList;
import java.util.List;

/**
 * ServletMapping Config
 * 类似web.xml中配置请求地址和具体servlet的映射关系
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 19:53
 */
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappings = new ArrayList<>(10);

    static {
        servletMappings.add(new ServletMapping("default", "/", DefaultServlet.class));
        servletMappings.add(new ServletMapping("favicon", "/favicon.ico", FaviconServlet.class));
    }
}
