package com.xinchen.spring.beans.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Resource resource = context.getResource(String resourceString) 能根据resourceString推测出需要的是哪种ResourceLoader
 *
 * <pre>
 *
 *    Prefix 	Example 	Explanation
 *    classpath: classpath:com/myapp/config.xml              Loaded from the classpath.
 *    file:      file:///data/config.xml                     Loaded as a URL from the filesystem. See also FileSystemResource Caveats.
 *    http:      https://myserver/logo.png                   Loaded as a URL.
 *    (none)     /data/config.xml                            Depends on the underlying ApplicationContext.
 * </pre>
 *
 * @author xinchen
 * @version 1.0
 * @date 15/08/2019 18:21
 */
public class Client {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        // Resource实际上是 UrlResource
        final InputStream inputStream = context.getResource("http://cn.bing.com/rs/30/1e/cj,nj/3f1e2270/f8c6dd44.js").getInputStream();

        final String collect = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));

        System.out.println(collect);


        final ResourceObject bean = context.getBean(ResourceObject.class);

        // 此处利用注入的方式，属性为org.springframework.core.io.Resource
        // class org.springframework.core.io.DefaultResourceLoader$ClassPathContextResource
        System.out.println(bean.getResource().getClass());
    }
}
