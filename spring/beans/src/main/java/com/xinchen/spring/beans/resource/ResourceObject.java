package com.xinchen.spring.beans.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 *
 * 当一个类实现ResourceLoaderAware并部署到应用程序上下文中时（作为Spring管理的bean），它被ResourceLoaderAware应用程序上下文识别。
 * 然后应用程序上下文调用setResourceLoader(ResourceLoader)，将自身作为参数提供（请记住，Spring中的所有应用程序上下文都实现了ResourceLoader接口）
 *
 * 由于a ApplicationContext是a ResourceLoader，bean也可以实现 ApplicationContextAware接口并直接使用提供的应用程序上下文来加载资源。
 *
 * @author xinchen
 * @version 1.0
 * @date 15/08/2019 21:19
 */
@Component
public class ResourceObject implements ResourceLoaderAware {

    private ApplicationContext context;

    @Value("service.xml")
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.context = (ApplicationContext) resourceLoader;
    }

    public ApplicationContext getContext() {
        return context;
    }
}
