package com.xinchen.spring.beans.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link RickService} 作为单例bean,接收Scope为prototype的{@link Rick}
 *
 * 每次调用{@link RickService#say()}生成新的{@link Rick}
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/25 23:05
 */
public class RickClient {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RickConfig.class);

        final RickService bean = context.getBean(RickService.class);
        for (int i = 0; i < 10; i++) {
            bean.say();
        }

        ((AnnotationConfigApplicationContext) context).close();
    }
}
