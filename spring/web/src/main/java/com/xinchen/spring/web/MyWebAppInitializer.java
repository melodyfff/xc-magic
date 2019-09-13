package com.xinchen.spring.web;

import com.xinchen.spring.web.config.AppConfig;
import com.xinchen.spring.web.config.RootConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

/**
 *
 * 配置DispatcherServlet,通过继承{@link AbstractAnnotationConfigDispatcherServletInitializer}
 *
 * 具体的实现在{@link AbstractDispatcherServletInitializer}中,其实也是实现了{@link WebApplicationInitializer}接口,做了一层封装
 *
 * 显示上下文层次结构,参考:
 *
 * https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/web.html#mvc-servlet-context-hierarchy
 *
 * 如果不需要应用程序上下文结构，应用程序可以通过getRootConfigClasses()返回所有配置,从getServletConfigClasses()返回null
 *
 *
 * <pre>
 *  等价的web.xml配置:
 *
 *  <web-app>
 *     <listener>
 *         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 *     </listener>
 *
 *     <context-param>
 *         <param-name>contextConfigLocation</param-name>
 *         <param-value>/WEB-INF/root-context.xml</param-value>
 *     </context-param>
 *
 *     <servlet>
 *         <servlet-name>app1</servlet-name>
 *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 *         <init-param>
 *             <param-name>contextConfigLocation</param-name>
 *             <param-value>/WEB-INF/app1-context.xml</param-value>
 *         </init-param>
 *         <load-on-startup>1</load-on-startup>
 *     </servlet>
 *
 *     <servlet-mapping>
 *         <servlet-name>app1</servlet-name>
 *         <url-pattern>/app1/*</url-pattern>
 *     </servlet-mapping>
 *
 * </web-app>
 *
 * </pre>
 *
 * <pre>
 *     {@link SafeVarargs} 注解只能用在参数长度可变的方法或构造方法上，且方法必须声明为static或final，否则会出现编译错误
 *     可通过加参数: -Xlint:unchecked 排查代码问题
 * </pre>
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
 * </code> *
 *
 * @author xinchen
 * @version 1.0
 * @date 11/09/2019 11:16
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 配置spring环境Root环境ApplicationContext
        return of(RootConfig.class);
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // 如果想要层次结构,这里直接返回null就行
        return of(AppConfig.class);
    }

    @Override
    protected String[] getServletMappings() {
        return of("/app1/*");
    }


    @Override
    protected String getServletName() {
        // 设置servlet名字
        return "app-dispatcher-AbstractAnnotationConfigDispatcherServletInitializer";
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 预留钩子,对ServletRegistration.Dynamic 作一些自定义配置,这里的启动顺序默认是1
        registration.setLoadOnStartup(2);
        // 设置在debug/trace日志级别的时候打印请求的详细信息
         registration.setInitParameter("enableLoggingRequestDetails", "true");
    }

    @SafeVarargs
    private static <T> T[] of(T... clz){
        return clz;
    }
}
