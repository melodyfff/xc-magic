package com.xinchen.spring.web;

import com.xinchen.spring.web.config.RootConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 *
 *
 * 配置DispatcherServlet,通过实现{@link WebApplicationInitializer#onStartup(ServletContext)}
 *
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
 *
 * 关于{@link DispatcherServlet#initStrategies(org.springframework.context.ApplicationContext)}
 * 这个地方是初始化官方的Special Bean Types类型的地方,主要支持如下特殊的bean
 *
 * <code>
 * protected void initStrategies(ApplicationContext context) {
 * 		initMultipartResolver(context);
 * 		initLocaleResolver(context);
 * 		initThemeResolver(context);
 * 		initHandlerMappings(context);
 * 		initHandlerAdapters(context);
 * 		initHandlerExceptionResolvers(context);
 * 		initRequestToViewNameTranslator(context);
 * 		initViewResolvers(context);
 * 		initFlashMapManager(context);
 *  }
 * </code>
 *
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
        final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        rootContext.refresh();


        // 由于采用了2种方式实现WebApplicationInitializer
        // 如果加上监听器,则会报错: Cannot initialize context because there is already a root application context present
        // org.springframework.web.context.ContextLoader#initWebApplicationContext(ServletContext servletContext)中会检测是否已经存在root config了
        // servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE)


        // servletContext.addListener(new ContextLoaderListener(rootContext));

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(rootContext);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("app-dispatcher-WebApplicationInitializer", servlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/app/*");
    }
}
