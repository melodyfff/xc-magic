package com.xinchen.web.server.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 类似web.xml中配置请求地址和具体servlet的映射关系
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/24 19:51
 */
@Data
@AllArgsConstructor
public class ServletMapping {
    private String servletName;
    private String url;
    private Class clazz;
}
