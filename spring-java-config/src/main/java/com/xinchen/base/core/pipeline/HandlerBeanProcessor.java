package com.xinchen.base.core.pipeline;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/10 23:32
 */
@Component
public class HandlerBeanProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext context;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 该方法会在一个bean初始化完成后调用，这里主要是在Pipeline初始化完成之后获取所有实现了
     * Handler接口的bean，然后通过调用Pipeline.addLast()方法将其添加到pipeline中
     * @param bean bean
     * @param beanName beanName
     * @return Object
     * @throws BeansException BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DefaultPipeline) {
            DefaultPipeline pipeline = (DefaultPipeline) bean;
            Map<String, Handler> handlerMap = context.getBeansOfType(Handler.class);
            // 向链中添加节点
            handlerMap.forEach((name, handler) -> pipeline.addLast(handler));
        }

        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
