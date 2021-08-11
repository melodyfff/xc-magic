package com.xinchen.spring.beans.annotation;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @date 2021-08-09 10:09
 */
class MyBeanDefinitionRegistryPostProcessorTest {

  @Test
  void test(){
    final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        MyBeanDefinitionRegistryPostProcessor.class);

    annotationConfigApplicationContext.start();

    final NestedTestBean bean = annotationConfigApplicationContext.getBean(NestedTestBean.class);
    final Object ccc = annotationConfigApplicationContext.getBean("nestedTestBean");

    assertThat(bean).isSameAs(ccc);

    System.out.println(ccc);
    System.out.println(((NestedTestBean)ccc).getCs());

    annotationConfigApplicationContext.close();
  }
}