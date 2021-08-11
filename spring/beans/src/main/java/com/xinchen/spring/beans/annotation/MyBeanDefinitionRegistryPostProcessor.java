package com.xinchen.spring.beans.annotation;

import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 自定义注解，能被Spring注入识别，并构建依赖的依赖
 *
 * @date 2021-08-06 17:08
 */
@Configuration
@ComponentScan(basePackageClasses = MyBeanDefinitionRegistryPostProcessor.class)
class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
  private final Reflections reflections = new Reflections(
      new ConfigurationBuilder()
          .forPackages("com.xinchen.spring.beans")
          .addScanners(new TypeAnnotationsScanner())
  );

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {

    printBeanDefintionNames(registry);

    //定义一个扫描器
//    ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
//    scanner.addIncludeFilter(new AnnotationTypeFilter(Api.class));
//    scanner.scan(MyBeanDefinitionRegistryPostProcessor.class.getSimpleName());

    // 注册自定义注解
    final Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Api.class);
    System.out.println(typesAnnotatedWith);
    typesAnnotatedWith.forEach(antypesAnnotatedWith-> registry.registerBeanDefinition(antypesAnnotatedWith.getAnnotation(Api.class).value(),new RootBeanDefinition(
        antypesAnnotatedWith)));

  }


  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {

  }

  private void printBeanDefintionNames(BeanDefinitionRegistry registry) {
    String[] beanDefintionNames = registry.getBeanDefinitionNames();
    for(String beanDefinitonName : beanDefintionNames){
      BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitonName);
      System.out.println(beanDefinition);
    }
  }
}
