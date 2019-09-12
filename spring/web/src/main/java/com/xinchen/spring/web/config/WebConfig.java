package com.xinchen.spring.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import java.util.List;

/**
 * {@link EnableWebMvc} 类似于xml中的配置,通过这个注解开启MVC Configuration
 *
 * <pre>
 *     <mvc:annotation-driven/>
 * </pre>
 *
 * 这个注解其实主要是 @Import(DelegatingWebMvcConfiguration.class), 委派给spring进行mvc的相关配置
 *
 * 关键代码是:
 *
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
        registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 数据类型(Content Types) ,

        // 默认会添加xml的
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
    }
}
