package com.xinchen.base.core.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * 需要每次都新创建的对象,交托给spring管理
 *
 * @author xinchen
 * @version 1.0
 * @date 12/06/2019 17:47
 */
//@Component
//@Scope(scopeName = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MultipleBean extends ApplicationObjectSupport {
    private Date date;


    public MultipleBean() {
        this.date = new Date();
    }


    public void hello(){
        System.out.println(date);
    }
}
