package com.xinchen.spring.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 Config
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/18 23:12
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xinchen.spring.integration"))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://$HOST:$PORT/swagger-ui.html
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("Magic")
                // 描述信息
                .description("Spring Integration Call Web Page")
                // 团队信息
                // .termsOfServiceUrl("http://localhost.com")
                // 创建人
                .contact(new Contact("xinchen","","xinchenmelody@gmail.com"))
                // 版本
                .version("1.0")
                // 拓展信息
                // .extensions(List< VendorExtension > extensions)
                .build();
    }
}
