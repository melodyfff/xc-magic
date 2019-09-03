package com.xinchen.spring.api;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 *
 * 混合类型 希望能够将建议的对象转换Lockable为其类型,并调用锁定和解锁方法
 *
 * 如果我们调用该lock()方法，我们希望所有setter方法都抛出一个LockedException
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 11:10
 */
public class LockMixin extends DelegatingIntroductionInterceptor implements Lockable{

    private boolean locked;

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public void unlock() {
        this.locked = false;
    }

    @Override
    public boolean locked() {
        return this.locked;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 如果处于锁定模式，则不能调用setter方法
        if (locked()&&invocation.getMethod().getName().indexOf("set")==0){
            throw new LockedException("this object had been locked.");
        }
        return super.invoke(invocation);
    }
}
