package com.xinchen.spring.web;

import com.xinchen.spring.web.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 *
 * 该配置类似于web.xml
 *
 * <pre>
 * <web-app>
 *     <listener>
 *         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 *     </listener>
 *
 *     <context-param>
 *         <param-name>contextConfigLocation</param-name>
 *         <param-value>/WEB-INF/app-context.xml</param-value>
 *     </context-param>
 *
 *     <servlet>
 *         <servlet-name>app</servlet-name>
 *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 *         <init-param>
 *             <param-name>contextConfigLocation</param-name>
 *             <param-value></param-value>
 *         </init-param>
 *         <load-on-startup>1</load-on-startup>
 *     </servlet>
 *
 *     <servlet-mapping>
 *         <servlet-name>app</servlet-name>
 *         <url-pattern>/app/*</url-pattern>
 *     </servlet-mapping>
 * </web-app>
 * </pre>
 *
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/10 23:27
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Load Spring web application configuration
        final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ctx);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}
