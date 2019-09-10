package com.xinchen.spring.targetsource;

import com.xinchen.spring.targetsource.detadata.BusinessService;
import com.xinchen.spring.targetsource.detadata.PersonService;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 热拔插例子 HotSwappableTargetSourceDemo
 *
 *
 * proxy对象代理的不是target而是targetSource
 *
 * 具体配置可看{@link swapper/spring-hot-swapper.xml}
 *
 *
 * {@link HotSwappableTargetSource} 存在让一个AOP代理的目标进行切换，同时让调用者保持自己对它的引用
 *
 * 更改目标源的目标会立即生效。{@link HotSwappableTargetSource}是thread-safe
 *
 * 通过调用swap()改变目标
 *
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 17:32
 */
public class HotSwappableTargetSourceDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("swapper/spring-hot-swapper.xml");

        // 获取代理类
        PersonService swappable = (PersonService) context.getBean("swappable");

        // com.xinchen.spring.targetsource.detadata.PersonService@462d5aee-> action()
        swappable.action();

        // 初始化原对象
        PersonService initialTarget = (PersonService) context.getBean("initialTarget");

        // 转换目标对象
        HotSwappableTargetSource swapper  = (HotSwappableTargetSource) context.getBean("swapper");
        swapper.swap(new PersonService());

        // com.xinchen.spring.targetsource.detadata.PersonService@69b0fd6f-> action()
        swappable.action();

        // 切换为原始对象
        swapper.swap(initialTarget);

        // com.xinchen.spring.targetsource.detadata.PersonService@462d5aee-> action()
        swappable.action();


        // 尝试转换为其他type
        swapper.swap(new BusinessService());

        // 报错cannot be cast to
        // 结论: 如果继承了统一接口或者有相同父类应该可以被转换
        ((BusinessService)context.getBean("swappable")).hello();

    }
}
