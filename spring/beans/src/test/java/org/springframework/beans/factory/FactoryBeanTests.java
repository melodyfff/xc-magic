package org.springframework.beans.factory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.beans.factory.ResourceTestUtils.qualifiedResource;

/**
 *
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 11/11/2020 14:45
 */
public class FactoryBeanTests {
    private static final Class<?> CLASS = FactoryBeanTests.class;
    private static final Resource RETURNS_NULL_CONTEXT = qualifiedResource(CLASS, "returnsNull.xml");
    private static final Resource WITH_AUTOWIRING_CONTEXT = qualifiedResource(CLASS, "withAutowiring.xml");

    @Test
    public void testFactoryBeanReturnsNull() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(factory).loadBeanDefinitions(RETURNS_NULL_CONTEXT);

        assertThat(factory.getBean("factoryBean").toString()).isEqualTo("null");
    }


    @Test
    public void testFactoryBeansWithAutowiring() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(factory).loadBeanDefinitions(WITH_AUTOWIRING_CONTEXT);

        BeanFactoryPostProcessor ppc = (BeanFactoryPostProcessor) factory.getBean("propertyPlaceholderConfigurer");
        ppc.postProcessBeanFactory(factory);

        Assertions.assertThat(factory.getType("betaFactory")).isNull();

        Alpha alpha = (Alpha) factory.getBean("alpha");
        Beta beta = (Beta) factory.getBean("beta");
        Gamma gamma = (Gamma) factory.getBean("gamma");
        Gamma gamma2 = (Gamma) factory.getBean("gammaFactory");

        Assertions.assertThat(alpha.getBeta()).isSameAs(beta);
        Assertions.assertThat(beta.getGamma()).isSameAs(gamma);
        Assertions.assertThat(beta.getGamma()).isSameAs(gamma2);
        Assertions.assertThat(beta.getName()).isEqualTo("yourName");
    }


    static class NullReturningFactoryBean implements FactoryBean<Object> {

        @Override
        public Object getObject() {
            return null;
        }

        @Override
        public Class<?> getObjectType() {
            return null;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }

    public static class Alpha implements InitializingBean {

        private Beta beta;

        public void setBeta(Beta beta) {
            this.beta = beta;
        }

        public Beta getBeta() {
            return beta;
        }

        @Override
        public void afterPropertiesSet() {
            Assert.notNull(beta, "'beta' property is required");
        }
    }


    public static class Beta implements InitializingBean {

        private Gamma gamma;

        private String name;

        public void setGamma(Gamma gamma) {
            this.gamma = gamma;
        }

        public Gamma getGamma() {
            return gamma;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void afterPropertiesSet() {
            Assert.notNull(gamma, "'gamma' property is required");
        }
    }


    public static class Gamma {
    }

    @Component
    public static class BetaFactoryBean implements FactoryBean<Object> {

        public BetaFactoryBean(Alpha alpha) {
        }

        private Beta beta;

        public void setBeta(Beta beta) {
            this.beta = beta;
        }

        @Override
        public Object getObject() {
            return this.beta;
        }

        @Override
        public Class<?> getObjectType() {
            return null;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }
}
