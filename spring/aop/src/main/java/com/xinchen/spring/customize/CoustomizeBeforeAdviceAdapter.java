package com.xinchen.spring.customize;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.adapter.AdvisorAdapter;

/**
 *
 *
 * 可参考{@link org.springframework.aop.framework.adapter} 包下的实现
 *
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 13:47
 */
public class CoustomizeBeforeAdviceAdapter implements AdvisorAdapter {

    @Override
    public boolean supportsAdvice(Advice advice) {
        // 条件判断
        return (advice instanceof CustomizeBeforeAdvice);
    }

    @Override
    public MethodInterceptor getInterceptor(Advisor advisor) {
        CustomizeBeforeAdvice advice = (CustomizeBeforeAdvice) advisor.getAdvice();
        return new CustomizeBeforeAdviceInterceptor(advice);
    }

}
