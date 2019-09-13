package com.xinchen.spring.web.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link EnableWebMvc} 类似于xml中的配置,通过这个注解开启MVC Configuration
 *
 *
 * <pre>
 *     <mvc:annotation-driven/>
 * </pre>
 *
 * 这个注解其实主要是 @Import(DelegatingWebMvcConfiguration.class), 委派给spring进行mvc的相关配置
 *
 * 所以可以存在多个mvcConfig,关键代码是:
 * <pre>
 *  @Autowired(required = false)
 * 	public void setConfigurers(List<WebMvcConfigurer> configurers) {
 * 		if (!CollectionUtils.isEmpty(configurers)) {
 * 			this.configurers.addWebMvcConfigurers(configurers);
 *        }
 *  }
 * </pre>
 *
 * 可以通过实现{@link WebMvcConfigurer} 来配置mvc config,也可以通过继承{@link WebMvcConfigurerAdapter},覆盖相关方法进行配置
 * {@link WebMvcConfigurerAdapter}中返回的是empty methods
 * 在{@link WebMvcConfigurationSupport}中可观察完整的配置过程
 *
 * @author xinchen
 * @version 1.0
 * @date 12/09/2019 12:18
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 类型转换(Type Conversion) ,参见FormattingConversionServiceFactoryBean获取更多

        // 默认情况下会添加对Number and Date types的支持,包括对@NumberFormat和@DateTimeFormat注解的支持
        // 详情参考DefaultFormattingConversionService#addDefaultFormatters(FormatterRegistry formatterRegistry)
        // 这里可添加自定义的一些格式转换
        super.addFormatters(registry);
    }

    @Override
    public Validator getValidator() {
        // 验证(Validation) ,参见LocalValidatorFactoryBean获取更多

        // 提供自定义Validator钩子而不是创建默认的,返回null保持默认
        // 可参考WebMvcConfigurerComposite#getValidator()方法,观察里面的选择,只有一个Validator生效


        // 也可以在Controller中绑定
        // @Controller
        // public class MyController {
        //     @InitBinder
        //     protected void initBinder(WebDataBinder binder) {
        //         binder.addValidators(new FooValidator());
        //     }
        // }

        // 如果使用@Bean的方式注入,可查看LocalValidatorFactoryBean中相关的注入,通常需要添加@Primary

        return null;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器(Interceptors) ,参见WebRequestHandlerInterceptorAdapter和InterceptorRegistry获取更多

        // WebMvcConfigurationSupport#getInterceptors()观察添加过程,会默认添加ConversionServiceExposingInterceptor()和ResourceUrlProviderExposingInterceptor()
        // WebRequestHandlerInterceptorAdapter类中可以查看具体的拦截器生效过程
        // 自定义拦截器可继承HandlerInterceptorAdapter 针对preHandle，postHandle，afterCompletion
        // 拦截器(Interceptors)和过滤器(Filters)都是针对URL的,而AOP则是拦截的是更细致的元数据(包/类/方法/参数)
        registry.addInterceptor(new LocaleChangeInterceptor());

        // 路径匹配 /**(匹配所有) /app/*.x(匹配/app/路径下的所有.x) /app/o?k(匹配/app/路径下的o1k,oWK等)
        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 数据类型(Content Types) ，在WebMvcConfigurationSupport#requestMappingHandlerAdapter()中添加

        // 默认会添加xml的
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 消息转换(Message Converters) , 在WebMvcConfigurationSupport#requestMappingHandlerAdapter()中添加

        // 这里的参数converters可以参照WebMvcConfigurationSupport#getMessageConverters(),其实是传入的一个new ArrayList<HttpMessageConverter<?>>()
        // 如果不自定义消息转换则会调用WebMvcConfigurationSupport#addDefaultHttpMessageConverters(this.messageConverters);添加默认的消息转换
        // 这里如果添加了则会覆盖默认的消息转换，如果想进行拓展可以覆盖extendMessageConverters()方法进行添加
        super.configureMessageConverters(converters);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 消息转换(Message Converters) , 在WebMvcConfigurationSupport#requestMappingHandlerAdapter()中添加

        // 这里主要是进行拓展添加自定义的消息转换
        // 通过Jackson2ObjectMapperBuilder为MappingJackson2HttpMessageConverter和MappingJackson2XmlHttpMessageConverter配置公共配置，缩进/日期格式/注册module
        // 注： Jackson XML使用缩进除了jackson-dataformat-xml外还需要添加woodstox-core-asl
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                // 模块自动推断，参考： https://nkcoder.github.io/2019/06/21/jackson-parameter-name-module/
                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 视图控制器(View Controllers) , 查看WebMvcConfigurationSupport#viewControllerHandlerMapping()观察全过程

        // 类似于xml中的 <mvc:view-controller path="/" view-name="home"/>
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 视图解析器(View Resolvers) ， 查看WebMvcConfigurationSupport#mvcViewResolver()观察全过程

        // 开启JSP和Jackson作为视图解析，默认视图为JSON
        // xml中的配置如下
        // <mvc:view-resolvers>
        //     <mvc:content-negotiation>
        //         <mvc:default-views>
        //             <bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
        //         </mvc:default-views>
        //     </mvc:content-negotiation>
        //     <mvc:jsp/>
        // </mvc:view-resolvers>

        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.jsp();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源(Static Resources) , 查看WebMvcConfigurationSupport#resourceHandlerMapping()观察全过程

        // 官网查看更多： https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/web.html#mvc-config-static-resources
        // 由于设定了两个DispatcherServlet的servlet分别为app/和app1/ ， 所以访问该静态资源的路径可能是http://localhost:8080/app/resources/static/hello.js

        // xml中的配置： <mvc:resources mapping="/resources/**" location="/public, classpath:/static/" cache-period="31556926" />
        registry.addResourceHandler("/resources/**")
                // 静态资源本地路径
                .addResourceLocations("classpath:/static")
                // 指定资源处理程序所服务资源的缓存周期（以秒为单位）
                // The default is to not send any cache headers but to rely on last-modified timestamps only
                // 默认不发送任何缓存头，值依赖 `last-modified`
                .setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 默认Servlet(Default Servlet) ,查看WebMvcConfigurationSupport#defaultServletHandlerMapping()观察全过程

        // Spring MVC允许映射DispatcherServlet到 '/' （从而覆盖容器的默认Servlet的映射），同时仍然允许容器的默认Servlet处理静态资源请求。
        // 这里我们自定义了2个DispatcherServlet，可能效果不是很明显
        // 这里使用的是DefaultServletHttpRequestHandler，优先级别为Integer.MAX_VALUE,很低

        // xml中的配置： <mvc:default-servlet-handler/>

        // 这个方法开启后，会生成一个DefaultServletHttpRequestHandler
        // 最终会返回一个HandlerMapping，Order的级别为Integer.MAX_VALUE
        configurer.enable();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 路径匹配(Path Matching) ,观察WebMvcConfigurationSupport#mvcPathMatcher()和mvcUrlPathHelper()
        // 查看PathMatchConfigurer获取更多细节

        // 如果没有配置则默认使用AntPathMatcher()和UrlPathHelper(),这两个都注册为bean交给spring管理
        super.configurePathMatch(configurer);
    }
}
