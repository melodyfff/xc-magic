package com.xinchen.spring.module.base.config;

import com.xinchen.spring.module.base.filter.FilterConfiguration;
import com.xinchen.spring.module.base.interceptors.InterceptorConfiguration;
import com.xinchen.spring.module.base.jpa.JpaConfiguration;
import org.springframework.context.annotation.Import;

/**
 *
 * 基础环境装配
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 19:43
 */
@Import({JpaConfiguration.class, InterceptorConfiguration.class, FilterConfiguration.class})
public class BaseContextConfiguration{
}
