package com.xinchen.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 * Spring AOP提供使用{@link JoinPoint}类型获取连接点数据，
 *
 * 任何通知方法的第一个参数都可以是JoinPoint
 *
 * 环绕通知是{@link ProceedingJoinPoint}，{@link JoinPoint}子类
 *
 * {@link Order}  Spring AOP 采用和 AspectJ 一样的优先顺序来织入增强处理：在进入连接点时，
 * 高优先级的增强处理将先被织入；在退出连接点时，高优先级的增强处理会后被织入。
 *
 * 此处如果不加{@link Order} ,那么在{@link DemoAroundAspect} 中的{@link Around}切面执行顺序就会受到影响
 *
 * 参考: https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/core.html#aop-ataspectj-advice-ordering
 *
 *
 *
 * Aspect Running Sequence:
 * Success      : @Around -> @Before -> 执行方法 ->  @Around -> @After -> @AfterReturning
 * fail(error)  : @Around -> @Before -> 方法报错 ->  @After  -> @AfterThrowing
 *
 * @author xinchen
 * @version 1.0
 * @date 20/08/2019 15:31
 */
@Aspect
@Component
@Order(1)
public class DemoAspect  {

    @Before(value = "com.xinchen.spring.aspect.SystemArchitecture.inPackage()")
    public void doBefore(JoinPoint obj){

        System.out.format("\n\n> before cut : \nPointcut[%s] \nargs(%s) \ntarget[%s] \n", obj.toLongString(), Arrays.toString(obj.getArgs()),obj.getTarget());
        System.out.println("------------------------------");
    }


    @After("com.xinchen.spring.aspect.SystemArchitecture.one()")
    public void doAfter(JoinPoint obj){
        System.out.println("------------------------------");
        // 此次如果掉用 obj.getThis() 会抛出 java.lang.StackOverflowError
        // 和上面的before同时竞争同一对象造成死锁
        System.out.format("> after cut : \nPointcut[%s] \nargs(%s) \ntarget[%s] \n", obj.toLongString(), Arrays.toString(obj.getArgs()),obj.getTarget());
    }


    @AfterReturning(value = "com.xinchen.spring.aspect.SystemArchitecture.one()",returning = "param")
    public void doAfterReturn(JoinPoint obj,Object param){
        System.out.println("> return cut : "+ param);
    }

}
