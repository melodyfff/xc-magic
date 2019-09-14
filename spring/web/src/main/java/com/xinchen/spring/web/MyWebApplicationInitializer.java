package com.xinchen.spring.web;

import com.xinchen.spring.web.config.RootConfig;
import org.springframework.core.Ordered;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

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
 * 		initHandlerMappings(context);  // BeanNameUrlHandlerMapping和DefaultAnnotationHandlerMapping包含在框架中，处理程序将始终包含在{@link HandlerExecutionChain}中，可实现{@link Ordered}确保顺序
 * 	                                   // 关于其中的拦截器部分HandlerInterceptor，分别在{@link HandlerExecutionChain}中的applyPreHandle、applyPostHandle()、体现
 *
 * 		initHandlerAdapters(context);  // HandlerAdapters主要是调度HandlerExecutionChain中的Handler处理请求，屏蔽细节
 * 		initHandlerExceptionResolvers(context); // 主要是BeanName为`handlerExceptionResolver`的{@link HandlerExceptionResolverComposite},Order为Ordered.LOWEST_PRECEDENCE
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
        //rootContext.refresh();

        // 由于采用了2种方式实现WebApplicationInitializer
        // 如果加上监听器,则会报错: Cannot initialize context because there is already a root application context present
        // org.springframework.web.context.ContextLoader#initWebApplicationContext(ServletContext servletContext)中会检测是否已经存在root config了
        // servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE)


        // servletContext.addListener(new ContextLoaderListener(rootContext));

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(rootContext);

        // WebApplicationContext命名空间。默认值是[server-name]-servlet。
        servlet.setNamespace("app");

        // 开启当找不到处理处理请求的时候，抛出异常，这里可以方便我们统一处理异常如404等
        // 如果默认开启了<mvc:default-servlet-handler/> 则这里可能不会抛出异常，因为总会找默认的
        servlet.setThrowExceptionIfNoHandlerFound(true);


        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("app-dispatcher-WebApplicationInitializer", servlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/app/*");

        // 设置在debug/trace日志级别的时候打印请求的详细信息
        servletRegistration.setInitParameter("enableLoggingRequestDetails", "true");
    }
}
