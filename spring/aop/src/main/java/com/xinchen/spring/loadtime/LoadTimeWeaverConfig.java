package com.xinchen.spring.loadtime;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/08/2019 15:34
 */
@Configuration
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.AUTODETECT)
//@EnableAspectJAutoProxy
@EnableSpringConfigured
public class LoadTimeWeaverConfig implements LoadTimeWeavingConfigurer {

    @Bean(name = "entitlementCalculationService")
    public StubEntitlementCalculationService entitlementCalculationService(){
        return new StubEntitlementCalculationService();
    }

    @Override
    public LoadTimeWeaver getLoadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public ProfilingAspect profilingAspect(){
        return Aspects.aspectOf(ProfilingAspect.class);
    }

}
