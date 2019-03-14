package com.xinchen.base;

import com.xinchen.base.config.AppConfig;
import com.xinchen.base.config.WebConfig;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Code-based Servlet container initialization
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/2/28 23:56
 */
public class WebInitializer implements WebApplicationInitializer {
    /**
     * Tomcat will call onStartup() ,when starting up
     *
     * @param servletContext servletContext
     * @throws ServletException ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Create the 'root' Spring application context
        // rootContext.register(ServiceConfig.class, JPAConfig.class, SecurityConfig.class)
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Create thr dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(WebConfig.class);

        // solve Could not write JSON: could not initialize proxy
        final OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
        FilterRegistration.Dynamic filter = servletContext.addFilter("hibernateFilter", openEntityManagerInViewFilter);
        filter.addMappingForUrlPatterns(null,true,"/*");

        // Register and mapping the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",new DispatcherServlet(dispatcherServlet));
        // Load and Initialize when container start up.
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
