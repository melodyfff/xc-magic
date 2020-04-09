package com.xinchen.spring.module.base.annotation;

import com.xinchen.spring.module.base.jpa.JpaConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 基础环境导入
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 19:43
 */
public class BaseContextSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{JpaConfiguration.class.getName()};
    }
}
