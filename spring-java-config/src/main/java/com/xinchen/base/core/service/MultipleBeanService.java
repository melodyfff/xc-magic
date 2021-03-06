package com.xinchen.base.core.service;

import com.xinchen.base.core.vo.MultipleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author xinchen
 * @version 1.0
 * @date 12/06/2019 17:48
 */
@Component
@Slf4j
public class MultipleBeanService extends ApplicationObjectSupport {



    @Autowired
    @Qualifier("multipleBean")
    private MultipleBean multipleBean;

    /**
     * 当bean生命周期结束后调用销毁
     * 可显式调用
     */
    @PreDestroy
    void destroy(){
        log.info(">>> multipleBean [{}] destroy...", multipleBean);
        ((ConfigurableApplicationContext)getApplicationContext()).getBeanFactory().destroyBean("multipleBean",multipleBean);
    }



    public void test(){
        System.out.println(multipleBean);
        multipleBean.hello();

    }
}
