package org.springframework.beans.factory.annotation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.testfixture.beans.ITestBean;
import org.springframework.beans.testfixture.beans.IndexedTestBean;
import org.springframework.beans.testfixture.beans.NestedTestBean;
import org.springframework.beans.testfixture.beans.TestBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;

/**
 * @date 2021-08-02 13:50
 */
class AutowiredAnnotationBeanPostProcessorTests {
  private DefaultListableBeanFactory defaultListableBeanFactory;

  private AutowiredAnnotationBeanPostProcessor beanPostProcessor;

  @BeforeEach
  public void setup() {
    defaultListableBeanFactory = new DefaultListableBeanFactory();
    defaultListableBeanFactory.registerResolvableDependency(BeanFactory.class,
        defaultListableBeanFactory);

    beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    beanPostProcessor.setBeanFactory(defaultListableBeanFactory);

    defaultListableBeanFactory.addBeanPostProcessor(beanPostProcessor);
    defaultListableBeanFactory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
    defaultListableBeanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
  }

  @AfterEach
  public void close() {
    defaultListableBeanFactory.destroySingletons();
  }

  @Test
  public void testIncompleteBeanDefinition() {
    // 不完整的bean
    defaultListableBeanFactory.registerBeanDefinition("testBean", new GenericBeanDefinition());

    assertThatExceptionOfType(BeanCreationException.class).isThrownBy(() ->
            defaultListableBeanFactory.getBean("testBean"))
        .withRootCauseInstanceOf(IllegalStateException.class);
  }

  @Test
  public void testResourceInjection() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResourceInjectionBean.class);
    // 这里设置为prototype，每次新建
    beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);

    defaultListableBeanFactory.registerBeanDefinition("annotatedBean", beanDefinition);

    TestBean tb = new TestBean();
    defaultListableBeanFactory.registerSingleton("testBean", tb);

    // 以下获取的TestBean均为同一个
    ResourceInjectionBean bean = (ResourceInjectionBean) defaultListableBeanFactory.getBean("annotatedBean");
    assertThat(bean.getTestBean()).isSameAs(tb);
    assertThat(bean.getTestBean2()).isSameAs(tb);

    bean = (ResourceInjectionBean) defaultListableBeanFactory.getBean("annotatedBean");
    assertThat(bean.getTestBean()).isSameAs(tb);
    assertThat(bean.getTestBean2()).isSameAs(tb);
  }

  @Test
  public void testExtendedResourceInjection() {
    RootBeanDefinition bd = new RootBeanDefinition(TypedExtendedResourceInjectionBean.class);
    bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    defaultListableBeanFactory.registerBeanDefinition("annotatedBean", bd);

    TestBean tb = new TestBean();
    defaultListableBeanFactory.registerSingleton("testBean", tb);

    NestedTestBean ntb = new NestedTestBean();
    defaultListableBeanFactory.registerSingleton("nestedTestBean", ntb);

    TypedExtendedResourceInjectionBean bean = (TypedExtendedResourceInjectionBean) defaultListableBeanFactory.getBean("annotatedBean");
    assertThat(bean.getTestBean()).isSameAs(tb);
    assertThat(bean.getTestBean2()).isSameAs(tb);
    assertThat(bean.getTestBean3()).isSameAs(tb);
    assertThat(bean.getTestBean4()).isSameAs(tb);
    assertThat(bean.getNestedTestBean()).isSameAs(ntb);
    assertThat(bean.getBeanFactory()).isSameAs(defaultListableBeanFactory);

    bean = (TypedExtendedResourceInjectionBean) defaultListableBeanFactory.getBean("annotatedBean");
    assertThat(bean.getTestBean()).isSameAs(tb);
    assertThat(bean.getTestBean2()).isSameAs(tb);
    assertThat(bean.getTestBean3()).isSameAs(tb);
    assertThat(bean.getTestBean4()).isSameAs(tb);
    assertThat(bean.getNestedTestBean()).isSameAs(ntb);
    assertThat(bean.getBeanFactory()).isSameAs(defaultListableBeanFactory);

    String[] depBeans = defaultListableBeanFactory.getDependenciesForBean("annotatedBean");
    assertThat(depBeans.length).isEqualTo(2);
    assertThat(depBeans[0]).isEqualTo("testBean");
    assertThat(depBeans[1]).isEqualTo("nestedTestBean");
  }


  @Test
  public void testExtendedResourceInjectionWithDestruction() {
    defaultListableBeanFactory.registerBeanDefinition("annotatedBean", new RootBeanDefinition(TypedExtendedResourceInjectionBean.class));
    defaultListableBeanFactory.registerBeanDefinition("testBean", new RootBeanDefinition(TestBean.class));

    NestedTestBean ntb = new NestedTestBean();
    defaultListableBeanFactory.registerSingleton("nestedTestBean", ntb);

    TestBean tb = defaultListableBeanFactory.getBean("testBean", TestBean.class);
    TypedExtendedResourceInjectionBean bean = (TypedExtendedResourceInjectionBean) defaultListableBeanFactory.getBean("annotatedBean");
    assertThat(bean.getTestBean()).isSameAs(tb);
    assertThat(bean.getTestBean2()).isSameAs(tb);
    assertThat(bean.getTestBean3()).isSameAs(tb);
    assertThat(bean.getTestBean4()).isSameAs(tb);
    assertThat(bean.getNestedTestBean()).isSameAs(ntb);
    assertThat(bean.getBeanFactory()).isSameAs(defaultListableBeanFactory);

    assertThat(defaultListableBeanFactory.getDependenciesForBean("annotatedBean")).isEqualTo(new String[] {"testBean", "nestedTestBean"});
    defaultListableBeanFactory.destroySingleton("testBean");
    assertThat(defaultListableBeanFactory.containsSingleton("testBean")).isFalse();
    assertThat(defaultListableBeanFactory.containsSingleton("annotatedBean")).isFalse();
    assertThat(bean.destroyed).isTrue();
    assertThat(defaultListableBeanFactory.getDependenciesForBean("annotatedBean").length).isSameAs(0);
  }








  //============================================================================
  // TEST FIXTURES FOR AutowiredAnnotationBeanPostProcessorTests
  //============================================================================

  @Qualifier("testBean")
  private void testBeanQualifierProvider() {}

  @Qualifier("integerRepo")
  private Repository<?> integerRepositoryQualifierProvider;


  public static class ResourceInjectionBean {

    @Autowired(required = false)
    private TestBean testBean;

    private TestBean testBean2;

    @Autowired
    public void setTestBean2(TestBean testBean2) {
      if (this.testBean2 != null) {
        throw new IllegalStateException("Already called");
      }
      this.testBean2 = testBean2;
    }

    public TestBean getTestBean() {
      return this.testBean;
    }

    public TestBean getTestBean2() {
      return this.testBean2;
    }
  }


  static class NonPublicResourceInjectionBean<T> extends ResourceInjectionBean {

    @Autowired
    public final ITestBean testBean3 = null;

    private T nestedTestBean;

    private ITestBean testBean4;

    protected BeanFactory beanFactory;

    public boolean baseInjected = false;

    public NonPublicResourceInjectionBean() {
    }

    @Override
    @Autowired
    @Required
    @SuppressWarnings("deprecation")
    public void setTestBean2(TestBean testBean2) {
      super.setTestBean2(testBean2);
    }

    @Autowired
    private void inject(ITestBean testBean4, T nestedTestBean) {
      this.testBean4 = testBean4;
      this.nestedTestBean = nestedTestBean;
    }

    @Autowired
    private void inject(ITestBean testBean4) {
      this.baseInjected = true;
    }

    @Autowired
    protected void initBeanFactory(BeanFactory beanFactory) {
      this.beanFactory = beanFactory;
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }

    public ITestBean getTestBean4() {
      return this.testBean4;
    }

    public T getNestedTestBean() {
      return this.nestedTestBean;
    }

    public BeanFactory getBeanFactory() {
      return this.beanFactory;
    }
  }


  public static class TypedExtendedResourceInjectionBean extends NonPublicResourceInjectionBean<NestedTestBean>
      implements DisposableBean {

    public boolean destroyed = false;

    @Override
    public void destroy() {
      this.destroyed = true;
    }
  }


  public static class OverriddenExtendedResourceInjectionBean extends NonPublicResourceInjectionBean<NestedTestBean> {

    public boolean subInjected = false;

    @Override
    public void setTestBean2(TestBean testBean2) {
      super.setTestBean2(testBean2);
    }

    @Override
    protected void initBeanFactory(BeanFactory beanFactory) {
      this.beanFactory = beanFactory;
    }

    @Autowired
    private void inject(ITestBean testBean4) {
      this.subInjected = true;
    }
  }


  public interface InterfaceWithDefaultMethod {

    @Autowired
    void setTestBean2(TestBean testBean2);

    @Autowired
    default void injectDefault(ITestBean testBean4) {
      markSubInjected();
    }

    void markSubInjected();
  }


  public static class DefaultMethodResourceInjectionBean extends NonPublicResourceInjectionBean<NestedTestBean>
      implements InterfaceWithDefaultMethod {

    public boolean subInjected = false;

    @Override
    public void setTestBean2(TestBean testBean2) {
      super.setTestBean2(testBean2);
    }

    @Override
    protected void initBeanFactory(BeanFactory beanFactory) {
      this.beanFactory = beanFactory;
    }

    @Override
    public void markSubInjected() {
      subInjected = true;
    }
  }


  public static class OptionalResourceInjectionBean extends ResourceInjectionBean {

    @Autowired(required = false)
    protected ITestBean testBean3;

    private IndexedTestBean indexedTestBean;

    private NestedTestBean[] nestedTestBeans;

    @Autowired(required = false)
    public NestedTestBean[] nestedTestBeansField;

    private ITestBean testBean4;

    @Override
    @Autowired(required = false)
    public void setTestBean2(TestBean testBean2) {
      super.setTestBean2(testBean2);
    }

    @Autowired(required = false)
    private void inject(ITestBean testBean4, NestedTestBean[] nestedTestBeans, IndexedTestBean indexedTestBean) {
      this.testBean4 = testBean4;
      this.indexedTestBean = indexedTestBean;
      this.nestedTestBeans = nestedTestBeans;
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }

    public ITestBean getTestBean4() {
      return this.testBean4;
    }

    public IndexedTestBean getIndexedTestBean() {
      return this.indexedTestBean;
    }

    public NestedTestBean[] getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  public static class OptionalCollectionResourceInjectionBean extends ResourceInjectionBean {

    @Autowired(required = false)
    protected ITestBean testBean3;

    private IndexedTestBean indexedTestBean;

    private List<NestedTestBean> nestedTestBeans;

    public List<NestedTestBean> nestedTestBeansSetter;

    @Autowired(required = false)
    public List<NestedTestBean> nestedTestBeansField;

    private ITestBean testBean4;

    @Override
    @Autowired(required = false)
    public void setTestBean2(TestBean testBean2) {
      super.setTestBean2(testBean2);
    }

    @Autowired(required = false)
    private void inject(ITestBean testBean4, List<NestedTestBean> nestedTestBeans, IndexedTestBean indexedTestBean) {
      this.testBean4 = testBean4;
      this.indexedTestBean = indexedTestBean;
      this.nestedTestBeans = nestedTestBeans;
    }

    @Autowired(required = false)
    public void setNestedTestBeans(List<NestedTestBean> nestedTestBeans) {
      this.nestedTestBeansSetter = nestedTestBeans;
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }

    public ITestBean getTestBean4() {
      return this.testBean4;
    }

    public IndexedTestBean getIndexedTestBean() {
      return this.indexedTestBean;
    }

    public List<NestedTestBean> getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  public static class ConstructorResourceInjectionBean extends ResourceInjectionBean {

    @Autowired(required = false)
    protected ITestBean testBean3;

    private ITestBean testBean4;

    private NestedTestBean nestedTestBean;

    private ConfigurableListableBeanFactory beanFactory;

    public ConstructorResourceInjectionBean() {
      throw new UnsupportedOperationException();
    }

    public ConstructorResourceInjectionBean(ITestBean testBean3) {
      throw new UnsupportedOperationException();
    }

    @Autowired
    public ConstructorResourceInjectionBean(@Autowired(required = false) ITestBean testBean4,
        @Autowired(required = false) NestedTestBean nestedTestBean,
        ConfigurableListableBeanFactory beanFactory) {
      this.testBean4 = testBean4;
      this.nestedTestBean = nestedTestBean;
      this.beanFactory = beanFactory;
    }

    public ConstructorResourceInjectionBean(NestedTestBean nestedTestBean) {
      throw new UnsupportedOperationException();
    }

    public ConstructorResourceInjectionBean(ITestBean testBean3, ITestBean testBean4, NestedTestBean nestedTestBean) {
      throw new UnsupportedOperationException();
    }

    @Override
    @Autowired(required = false)
    public void setTestBean2(TestBean testBean2) {
      super.setTestBean2(testBean2);
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }

    public ITestBean getTestBean4() {
      return this.testBean4;
    }

    public NestedTestBean getNestedTestBean() {
      return this.nestedTestBean;
    }

    public ConfigurableListableBeanFactory getBeanFactory() {
      return this.beanFactory;
    }
  }


  public static class ConstructorsResourceInjectionBean {

    protected ITestBean testBean3;

    private ITestBean testBean4;

    private NestedTestBean[] nestedTestBeans;

    public ConstructorsResourceInjectionBean() {
    }

    @Autowired(required = false)
    public ConstructorsResourceInjectionBean(ITestBean testBean3) {
      this.testBean3 = testBean3;
    }

    @Autowired(required = false)
    public ConstructorsResourceInjectionBean(ITestBean testBean4, NestedTestBean[] nestedTestBeans) {
      this.testBean4 = testBean4;
      this.nestedTestBeans = nestedTestBeans;
    }

    public ConstructorsResourceInjectionBean(NestedTestBean nestedTestBean) {
      throw new UnsupportedOperationException();
    }

    public ConstructorsResourceInjectionBean(ITestBean testBean3, ITestBean testBean4, NestedTestBean nestedTestBean) {
      throw new UnsupportedOperationException();
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }

    public ITestBean getTestBean4() {
      return this.testBean4;
    }

    public NestedTestBean[] getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  public static class ConstructorWithoutFallbackBean {

    protected ITestBean testBean3;

    @Autowired(required = false)
    public ConstructorWithoutFallbackBean(ITestBean testBean3) {
      this.testBean3 = testBean3;
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }
  }


  public static class ConstructorsCollectionResourceInjectionBean {

    protected ITestBean testBean3;

    private ITestBean testBean4;

    private List<NestedTestBean> nestedTestBeans;

    public ConstructorsCollectionResourceInjectionBean() {
    }

    @Autowired(required = false)
    public ConstructorsCollectionResourceInjectionBean(ITestBean testBean3) {
      this.testBean3 = testBean3;
    }

    @Autowired(required = false)
    public ConstructorsCollectionResourceInjectionBean(ITestBean testBean4, List<NestedTestBean> nestedTestBeans) {
      this.testBean4 = testBean4;
      this.nestedTestBeans = nestedTestBeans;
    }

    public ConstructorsCollectionResourceInjectionBean(NestedTestBean nestedTestBean) {
      throw new UnsupportedOperationException();
    }

    public ConstructorsCollectionResourceInjectionBean(ITestBean testBean3, ITestBean testBean4,
        NestedTestBean nestedTestBean) {
      throw new UnsupportedOperationException();
    }

    public ITestBean getTestBean3() {
      return this.testBean3;
    }

    public ITestBean getTestBean4() {
      return this.testBean4;
    }

    public List<NestedTestBean> getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  public static class SingleConstructorVarargBean {

    private ITestBean testBean;

    private List<NestedTestBean> nestedTestBeans;

    public SingleConstructorVarargBean(ITestBean testBean, NestedTestBean... nestedTestBeans) {
      this.testBean = testBean;
      this.nestedTestBeans = Arrays.asList(nestedTestBeans);
    }

    public ITestBean getTestBean() {
      return this.testBean;
    }

    public List<NestedTestBean> getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  public static class SingleConstructorRequiredCollectionBean {

    private ITestBean testBean;

    private List<NestedTestBean> nestedTestBeans;

    public SingleConstructorRequiredCollectionBean(ITestBean testBean, List<NestedTestBean> nestedTestBeans) {
      this.testBean = testBean;
      this.nestedTestBeans = nestedTestBeans;
    }

    public ITestBean getTestBean() {
      return this.testBean;
    }

    public List<NestedTestBean> getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  public static class SingleConstructorOptionalCollectionBean {

    private ITestBean testBean;

    private List<NestedTestBean> nestedTestBeans;

    public SingleConstructorOptionalCollectionBean(ITestBean testBean,
        @Autowired(required = false) List<NestedTestBean> nestedTestBeans) {
      this.testBean = testBean;
      this.nestedTestBeans = nestedTestBeans;
    }

    public ITestBean getTestBean() {
      return this.testBean;
    }

    public List<NestedTestBean> getNestedTestBeans() {
      return this.nestedTestBeans;
    }
  }


  @SuppressWarnings("serial")
  public static class MyTestBeanMap extends LinkedHashMap<String, TestBean> {
  }


  @SuppressWarnings("serial")
  public static class MyTestBeanSet extends LinkedHashSet<TestBean> {
  }


  public static class MapConstructorInjectionBean {

    private Map<String, TestBean> testBeanMap;

    @Autowired
    public MapConstructorInjectionBean(Map<String, TestBean> testBeanMap) {
      this.testBeanMap = testBeanMap;
    }

    public Map<String, TestBean> getTestBeanMap() {
      return this.testBeanMap;
    }
  }


  public static class QualifiedMapConstructorInjectionBean {

    private Map<String, TestBean> testBeanMap;

    @Autowired
    public QualifiedMapConstructorInjectionBean(@Qualifier("myTestBeanMap") Map<String, TestBean> testBeanMap) {
      this.testBeanMap = testBeanMap;
    }

    public Map<String, TestBean> getTestBeanMap() {
      return this.testBeanMap;
    }
  }


  public static class SetConstructorInjectionBean {

    private Set<TestBean> testBeanSet;

    @Autowired
    public SetConstructorInjectionBean(Set<TestBean> testBeanSet) {
      this.testBeanSet = testBeanSet;
    }

    public Set<TestBean> getTestBeanSet() {
      return this.testBeanSet;
    }
  }


  public static class SelfInjectionBean {

    @Autowired
    public SelfInjectionBean reference;

    @Autowired(required = false)
    public List<SelfInjectionBean> referenceCollection;
  }


  @SuppressWarnings("serial")
  public static class SelfInjectionCollectionBean extends ArrayList<SelfInjectionCollectionBean> {

    @Autowired
    public SelfInjectionCollectionBean reference;

    @Autowired(required = false)
    public List<SelfInjectionCollectionBean> referenceCollection;
  }


  public static class MapFieldInjectionBean {

    @Autowired
    private Map<String, TestBean> testBeanMap;

    public Map<String, TestBean> getTestBeanMap() {
      return this.testBeanMap;
    }
  }


  public static class MapMethodInjectionBean {

    private TestBean testBean;

    private Map<String, TestBean> testBeanMap;

    @Autowired(required = false)
    public void setTestBeanMap(TestBean testBean, Map<String, TestBean> testBeanMap) {
      this.testBean = testBean;
      this.testBeanMap = testBeanMap;
    }

    public TestBean getTestBean() {
      return this.testBean;
    }

    public Map<String, TestBean> getTestBeanMap() {
      return this.testBeanMap;
    }
  }


  @SuppressWarnings("serial")
  public static class ObjectFactoryFieldInjectionBean implements Serializable {

    @Autowired
    private ObjectFactory<TestBean> testBeanFactory;

    public TestBean getTestBean() {
      return this.testBeanFactory.getObject();
    }
  }


  @SuppressWarnings("serial")
  public static class ObjectFactoryConstructorInjectionBean implements Serializable {

    private final ObjectFactory<TestBean> testBeanFactory;

    public ObjectFactoryConstructorInjectionBean(ObjectFactory<TestBean> testBeanFactory) {
      this.testBeanFactory = testBeanFactory;
    }

    public TestBean getTestBean() {
      return this.testBeanFactory.getObject();
    }
  }


  public static class ObjectFactoryQualifierInjectionBean {

    @Autowired
    @Qualifier("testBean")
    private ObjectFactory<?> testBeanFactory;

    public TestBean getTestBean() {
      return (TestBean) this.testBeanFactory.getObject();
    }
  }


  public static class ObjectProviderInjectionBean {

    @Autowired
    private ObjectProvider<TestBean> testBeanProvider;

    private TestBean consumedTestBean;

    public TestBean getTestBean() {
      return this.testBeanProvider.getObject();
    }

    public TestBean getTestBean(String name) {
      return this.testBeanProvider.getObject(name);
    }

    public TestBean getOptionalTestBean() {
      return this.testBeanProvider.getIfAvailable();
    }

    public TestBean getOptionalTestBeanWithDefault() {
      return this.testBeanProvider.getIfAvailable(() -> new TestBean("default"));
    }

    public TestBean consumeOptionalTestBean() {
      this.testBeanProvider.ifAvailable(tb -> consumedTestBean = tb);
      return consumedTestBean;
    }

    public TestBean getUniqueTestBean() {
      return this.testBeanProvider.getIfUnique();
    }

    public TestBean getUniqueTestBeanWithDefault() {
      return this.testBeanProvider.getIfUnique(() -> new TestBean("default"));
    }

    public TestBean consumeUniqueTestBean() {
      this.testBeanProvider.ifUnique(tb -> consumedTestBean = tb);
      return consumedTestBean;
    }

    public List<TestBean> iterateTestBeans() {
      List<TestBean> resolved = new ArrayList<>();
      for (TestBean tb : this.testBeanProvider) {
        resolved.add(tb);
      }
      return resolved;
    }

    public List<TestBean> forEachTestBeans() {
      List<TestBean> resolved = new ArrayList<>();
      this.testBeanProvider.forEach(resolved::add);
      return resolved;
    }

    public List<TestBean> streamTestBeans() {
      return this.testBeanProvider.stream().collect(Collectors.toList());
    }

    public List<TestBean> sortedTestBeans() {
      return this.testBeanProvider.orderedStream().collect(Collectors.toList());
    }
  }


  public static class CustomAnnotationRequiredFieldResourceInjectionBean {

    @MyAutowired(optional = false)
    private TestBean testBean;

    public TestBean getTestBean() {
      return this.testBean;
    }
  }


  public static class CustomAnnotationRequiredMethodResourceInjectionBean {

    private TestBean testBean;

    @MyAutowired(optional = false)
    public void setTestBean(TestBean testBean) {
      this.testBean = testBean;
    }

    public TestBean getTestBean() {
      return this.testBean;
    }
  }


  public static class CustomAnnotationOptionalFieldResourceInjectionBean extends ResourceInjectionBean {

    @MyAutowired(optional = true)
    private TestBean testBean3;

    public TestBean getTestBean3() {
      return this.testBean3;
    }
  }


  public static class CustomAnnotationOptionalMethodResourceInjectionBean extends ResourceInjectionBean {

    private TestBean testBean3;

    @MyAutowired(optional = true)
    protected void setTestBean3(TestBean testBean3) {
      this.testBean3 = testBean3;
    }

    public TestBean getTestBean3() {
      return this.testBean3;
    }
  }


  @Target({ElementType.METHOD, ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface MyAutowired {

    boolean optional() default false;
  }


  /**
   * Bean with a dependency on a {@link FactoryBean}.
   */
  private static class FactoryBeanDependentBean {

    @Autowired
    private FactoryBean<?> factoryBean;

    public final FactoryBean<?> getFactoryBean() {
      return this.factoryBean;
    }
  }


  public static class StringFactoryBean implements FactoryBean<String> {

    @Override
    public String getObject() throws Exception {
      return "";
    }

    @Override
    public Class<String> getObjectType() {
      return String.class;
    }

    @Override
    public boolean isSingleton() {
      return true;
    }
  }


  public static class OrderedNestedTestBean extends NestedTestBean implements Ordered {

    private int order;

    public void setOrder(int order) {
      this.order = order;
    }

    @Override
    public int getOrder() {
      return this.order;
    }
  }


  @Order(1)
  public static class FixedOrder1NestedTestBean extends NestedTestBean {
  }

  @Order(2)
  public static class FixedOrder2NestedTestBean extends NestedTestBean {
  }


  public interface Repository<T> {
  }

  public static class StringRepository implements Repository<String> {
  }

  public static class IntegerRepository implements Repository<Integer> {
  }

  public static class GenericRepository<T> implements Repository<T> {
  }

  @SuppressWarnings("rawtypes")
  public static class GenericRepositorySubclass extends GenericRepository {
  }

  @SuppressWarnings("rawtypes")
  public static class SimpleRepository implements Repository {
  }

  public static class SimpleRepositorySubclass extends SimpleRepository {
  }


  public static class RepositoryFactoryBean<T> implements FactoryBean<T> {

    @Override
    public T getObject() {
      throw new IllegalStateException();
    }

    @Override
    public Class<?> getObjectType() {
      return Object.class;
    }

    @Override
    public boolean isSingleton() {
      return false;
    }
  }


  public static class RepositoryFieldInjectionBean {

    @Autowired
    public String string;

    @Autowired
    public Integer integer;

    @Autowired
    public String[] stringArray;

    @Autowired
    public Integer[] integerArray;

    @Autowired
    public List<String> stringList;

    @Autowired
    public List<Integer> integerList;

    @Autowired
    public Map<String, String> stringMap;

    @Autowired
    public Map<String, Integer> integerMap;

    @Autowired
    public Repository<String> stringRepository;

    @Autowired
    public Repository<Integer> integerRepository;

    @Autowired
    public Repository<String>[] stringRepositoryArray;

    @Autowired
    public Repository<Integer>[] integerRepositoryArray;

    @Autowired
    public List<Repository<String>> stringRepositoryList;

    @Autowired
    public List<Repository<Integer>> integerRepositoryList;

    @Autowired
    public Map<String, Repository<String>> stringRepositoryMap;

    @Autowired
    public Map<String, Repository<Integer>> integerRepositoryMap;
  }


  public static class RepositoryFieldInjectionBeanWithVariables<S, I> {

    @Autowired
    public S string;

    @Autowired
    public I integer;

    @Autowired
    public S[] stringArray;

    @Autowired
    public I[] integerArray;

    @Autowired
    public List<S> stringList;

    @Autowired
    public List<I> integerList;

    @Autowired
    public Map<String, S> stringMap;

    @Autowired
    public Map<String, I> integerMap;

    @Autowired
    public Repository<S> stringRepository;

    @Autowired
    public Repository<I> integerRepository;

    @Autowired
    public Repository<S>[] stringRepositoryArray;

    @Autowired
    public Repository<I>[] integerRepositoryArray;

    @Autowired
    public List<Repository<S>> stringRepositoryList;

    @Autowired
    public List<Repository<I>> integerRepositoryList;

    @Autowired
    public Map<String, Repository<S>> stringRepositoryMap;

    @Autowired
    public Map<String, Repository<I>> integerRepositoryMap;
  }


  public static class RepositoryFieldInjectionBeanWithSubstitutedVariables
      extends RepositoryFieldInjectionBeanWithVariables<String, Integer> {
  }


  public static class RepositoryFieldInjectionBeanWithQualifiers {

    @Autowired @Qualifier("stringRepo")
    public Repository<?> stringRepository;

    @Autowired @Qualifier("integerRepo")
    public Repository<?> integerRepository;

    @Autowired @Qualifier("stringRepo")
    public Repository<?>[] stringRepositoryArray;

    @Autowired @Qualifier("integerRepo")
    public Repository<?>[] integerRepositoryArray;

    @Autowired @Qualifier("stringRepo")
    public List<Repository<?>> stringRepositoryList;

    @Autowired @Qualifier("integerRepo")
    public List<Repository<?>> integerRepositoryList;

    @Autowired @Qualifier("stringRepo")
    public Map<String, Repository<?>> stringRepositoryMap;

    @Autowired @Qualifier("integerRepo")
    public Map<String, Repository<?>> integerRepositoryMap;
  }


  public static class RepositoryFieldInjectionBeanWithSimpleMatch {

    @Autowired
    public Repository<?> repository;

    @Autowired
    public Repository<String> stringRepository;

    @Autowired
    public Repository<?>[] repositoryArray;

    @Autowired
    public Repository<String>[] stringRepositoryArray;

    @Autowired
    public List<Repository<?>> repositoryList;

    @Autowired
    public List<Repository<String>> stringRepositoryList;

    @Autowired
    public Map<String, Repository<?>> repositoryMap;

    @Autowired
    public Map<String, Repository<String>> stringRepositoryMap;
  }


  public static class RepositoryFactoryBeanInjectionBean {

    @Autowired
    public RepositoryFactoryBean<?> repositoryFactoryBean;
  }


  public static class RepositoryMethodInjectionBean {

    public String string;

    public Integer integer;

    public String[] stringArray;

    public Integer[] integerArray;

    public List<String> stringList;

    public List<Integer> integerList;

    public Map<String, String> stringMap;

    public Map<String, Integer> integerMap;

    public Repository<String> stringRepository;

    public Repository<Integer> integerRepository;

    public Repository<String>[] stringRepositoryArray;

    public Repository<Integer>[] integerRepositoryArray;

    public List<Repository<String>> stringRepositoryList;

    public List<Repository<Integer>> integerRepositoryList;

    public Map<String, Repository<String>> stringRepositoryMap;

    public Map<String, Repository<Integer>> integerRepositoryMap;

    @Autowired
    public void setString(String string) {
      this.string = string;
    }

    @Autowired
    public void setInteger(Integer integer) {
      this.integer = integer;
    }

    @Autowired
    public void setStringArray(String[] stringArray) {
      this.stringArray = stringArray;
    }

    @Autowired
    public void setIntegerArray(Integer[] integerArray) {
      this.integerArray = integerArray;
    }

    @Autowired
    public void setStringList(List<String> stringList) {
      this.stringList = stringList;
    }

    @Autowired
    public void setIntegerList(List<Integer> integerList) {
      this.integerList = integerList;
    }

    @Autowired
    public void setStringMap(Map<String, String> stringMap) {
      this.stringMap = stringMap;
    }

    @Autowired
    public void setIntegerMap(Map<String, Integer> integerMap) {
      this.integerMap = integerMap;
    }

    @Autowired
    public void setStringRepository(Repository<String> stringRepository) {
      this.stringRepository = stringRepository;
    }

    @Autowired
    public void setIntegerRepository(Repository<Integer> integerRepository) {
      this.integerRepository = integerRepository;
    }

    @Autowired
    public void setStringRepositoryArray(Repository<String>[] stringRepositoryArray) {
      this.stringRepositoryArray = stringRepositoryArray;
    }

    @Autowired
    public void setIntegerRepositoryArray(Repository<Integer>[] integerRepositoryArray) {
      this.integerRepositoryArray = integerRepositoryArray;
    }

    @Autowired
    public void setStringRepositoryList(List<Repository<String>> stringRepositoryList) {
      this.stringRepositoryList = stringRepositoryList;
    }

    @Autowired
    public void setIntegerRepositoryList(List<Repository<Integer>> integerRepositoryList) {
      this.integerRepositoryList = integerRepositoryList;
    }

    @Autowired
    public void setStringRepositoryMap(Map<String, Repository<String>> stringRepositoryMap) {
      this.stringRepositoryMap = stringRepositoryMap;
    }

    @Autowired
    public void setIntegerRepositoryMap(Map<String, Repository<Integer>> integerRepositoryMap) {
      this.integerRepositoryMap = integerRepositoryMap;
    }
  }


  public static class RepositoryMethodInjectionBeanWithVariables<S, I> {

    public S string;

    public I integer;

    public S[] stringArray;

    public I[] integerArray;

    public List<S> stringList;

    public List<I> integerList;

    public Map<String, S> stringMap;

    public Map<String, I> integerMap;

    public Repository<S> stringRepository;

    public Repository<I> integerRepository;

    public Repository<S>[] stringRepositoryArray;

    public Repository<I>[] integerRepositoryArray;

    public List<Repository<S>> stringRepositoryList;

    public List<Repository<I>> integerRepositoryList;

    public Map<String, Repository<S>> stringRepositoryMap;

    public Map<String, Repository<I>> integerRepositoryMap;

    @Autowired
    public void setString(S string) {
      this.string = string;
    }

    @Autowired
    public void setInteger(I integer) {
      this.integer = integer;
    }

    @Autowired
    public void setStringArray(S[] stringArray) {
      this.stringArray = stringArray;
    }

    @Autowired
    public void setIntegerArray(I[] integerArray) {
      this.integerArray = integerArray;
    }

    @Autowired
    public void setStringList(List<S> stringList) {
      this.stringList = stringList;
    }

    @Autowired
    public void setIntegerList(List<I> integerList) {
      this.integerList = integerList;
    }

    @Autowired
    public void setStringMap(Map<String, S> stringMap) {
      this.stringMap = stringMap;
    }

    @Autowired
    public void setIntegerMap(Map<String, I> integerMap) {
      this.integerMap = integerMap;
    }

    @Autowired
    public void setStringRepository(Repository<S> stringRepository) {
      this.stringRepository = stringRepository;
    }

    @Autowired
    public void setIntegerRepository(Repository<I> integerRepository) {
      this.integerRepository = integerRepository;
    }

    @Autowired
    public void setStringRepositoryArray(Repository<S>[] stringRepositoryArray) {
      this.stringRepositoryArray = stringRepositoryArray;
    }

    @Autowired
    public void setIntegerRepositoryArray(Repository<I>[] integerRepositoryArray) {
      this.integerRepositoryArray = integerRepositoryArray;
    }

    @Autowired
    public void setStringRepositoryList(List<Repository<S>> stringRepositoryList) {
      this.stringRepositoryList = stringRepositoryList;
    }

    @Autowired
    public void setIntegerRepositoryList(List<Repository<I>> integerRepositoryList) {
      this.integerRepositoryList = integerRepositoryList;
    }

    @Autowired
    public void setStringRepositoryMap(Map<String, Repository<S>> stringRepositoryMap) {
      this.stringRepositoryMap = stringRepositoryMap;
    }

    @Autowired
    public void setIntegerRepositoryMap(Map<String, Repository<I>> integerRepositoryMap) {
      this.integerRepositoryMap = integerRepositoryMap;
    }
  }


  public static class RepositoryMethodInjectionBeanWithSubstitutedVariables
      extends RepositoryMethodInjectionBeanWithVariables<String, Integer> {
  }


  public static class RepositoryConstructorInjectionBean {

    public Repository<String> stringRepository;

    public Repository<Integer> integerRepository;

    public Repository<String>[] stringRepositoryArray;

    public Repository<Integer>[] integerRepositoryArray;

    public List<Repository<String>> stringRepositoryList;

    public List<Repository<Integer>> integerRepositoryList;

    public Map<String, Repository<String>> stringRepositoryMap;

    public Map<String, Repository<Integer>> integerRepositoryMap;

    @Autowired
    public RepositoryConstructorInjectionBean(Repository<String> stringRepository, Repository<Integer> integerRepository,
        Repository<String>[] stringRepositoryArray, Repository<Integer>[] integerRepositoryArray,
        List<Repository<String>> stringRepositoryList, List<Repository<Integer>> integerRepositoryList,
        Map<String, Repository<String>> stringRepositoryMap, Map<String, Repository<Integer>> integerRepositoryMap) {
      this.stringRepository = stringRepository;
      this.integerRepository = integerRepository;
      this.stringRepositoryArray = stringRepositoryArray;
      this.integerRepositoryArray = integerRepositoryArray;
      this.stringRepositoryList = stringRepositoryList;
      this.integerRepositoryList = integerRepositoryList;
      this.stringRepositoryMap = stringRepositoryMap;
      this.integerRepositoryMap = integerRepositoryMap;
    }
  }


  /**
   * Pseudo-implementation of EasyMock's {@code MocksControl} class.
   */
  public static class MocksControl {

    @SuppressWarnings("unchecked")
    public <T> T createMock(Class<T> toMock) {
      return (T) Proxy.newProxyInstance(AutowiredAnnotationBeanPostProcessorTests.class.getClassLoader(), new Class<?>[] {toMock},
          new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              throw new UnsupportedOperationException("mocked!");
            }
          });
    }
  }


  public interface GenericInterface1<T> {

    String doSomethingGeneric(T o);
  }


  public static class GenericInterface1Impl<T> implements GenericInterface1<T> {

    @Autowired
    private GenericInterface2<T> gi2;

    @Override
    public String doSomethingGeneric(T o) {
      return gi2.doSomethingMoreGeneric(o) + "_somethingGeneric_" + o;
    }

    public static GenericInterface1<String> create() {
      return new StringGenericInterface1Impl();
    }

    public static GenericInterface1<String> createErased() {
      return new GenericInterface1Impl<>();
    }

    @SuppressWarnings("rawtypes")
    public static GenericInterface1 createPlain() {
      return new GenericInterface1Impl();
    }
  }


  public static class StringGenericInterface1Impl extends GenericInterface1Impl<String> {
  }


  public interface GenericInterface2<K> {

    String doSomethingMoreGeneric(K o);
  }


  public static class GenericInterface2Impl implements GenericInterface2<String> {

    @Override
    public String doSomethingMoreGeneric(String o) {
      return "somethingMoreGeneric_" + o;
    }
  }


  public static class ReallyGenericInterface2Impl implements GenericInterface2<Object> {

    @Override
    public String doSomethingMoreGeneric(Object o) {
      return "somethingMoreGeneric_" + o;
    }
  }


  public static class GenericInterface2Bean<K> implements GenericInterface2<K>, BeanNameAware {

    private String name;

    @Override
    public void setBeanName(String name) {
      this.name = name;
    }

    @Override
    public String doSomethingMoreGeneric(K o) {
      return this.name + " " + o;
    }
  }


  public static class MultiGenericFieldInjection {

    @Autowired
    private GenericInterface2<String> stringBean;

    @Autowired
    private GenericInterface2<Integer> integerBean;

    @Override
    public String toString() {
      return this.stringBean.doSomethingMoreGeneric("a") + " " + this.integerBean.doSomethingMoreGeneric(123);
    }
  }


  @SuppressWarnings("rawtypes")
  public static class PlainGenericInterface2Impl implements GenericInterface2 {

    @Override
    public String doSomethingMoreGeneric(Object o) {
      return "somethingMoreGeneric_" + o;
    }
  }


  @SuppressWarnings("rawtypes")
  public interface StockMovement<P extends StockMovementInstruction> {
  }


  @SuppressWarnings("rawtypes")
  public interface StockMovementInstruction<C extends StockMovement> {
  }


  @SuppressWarnings("rawtypes")
  public interface StockMovementDao<S extends StockMovement> {
  }


  @SuppressWarnings("rawtypes")
  public static class StockMovementImpl<P extends StockMovementInstruction> implements StockMovement<P> {
  }


  @SuppressWarnings("rawtypes")
  public static class StockMovementInstructionImpl<C extends StockMovement> implements StockMovementInstruction<C> {
  }


  @SuppressWarnings("rawtypes")
  public static class StockMovementDaoImpl<E extends StockMovement> implements StockMovementDao<E> {
  }


  public static class StockServiceImpl {

    @Autowired
    @SuppressWarnings("rawtypes")
    private StockMovementDao<StockMovement> stockMovementDao;
  }


  public static class MyCallable implements Callable<Thread> {

    @Override
    public Thread call() throws Exception {
      return null;
    }
  }


  public static class SecondCallable implements Callable<Thread> {

    @Override
    public Thread call() throws Exception {
      return null;
    }
  }


  public static abstract class Foo<T extends Runnable, RT extends Callable<T>> {

    private RT obj;

    protected void setObj(RT obj) {
      if (this.obj != null) {
        throw new IllegalStateException("Already called");
      }
      this.obj = obj;
    }
  }


  public static class FooBar extends Foo<Thread, MyCallable> {

    @Override
    @Autowired
    public void setObj(MyCallable obj) {
      super.setObj(obj);
    }
  }


  public static class NullNestedTestBeanFactoryBean implements FactoryBean<NestedTestBean> {

    @Override
    public NestedTestBean getObject() {
      return null;
    }

    @Override
    public Class<?> getObjectType() {
      return NestedTestBean.class;
    }

    @Override
    public boolean isSingleton() {
      return true;
    }
  }


  public static class NullFactoryMethods {

    public static TestBean createTestBean() {
      return null;
    }

    public static NestedTestBean createNestedTestBean() {
      return null;
    }
  }


  public static class ProvidedArgumentBean {

    public ProvidedArgumentBean(String[] args) {
    }
  }


  public static class CollectionFactoryMethods {

    public static Map<String, TestBean> testBeanMap() {
      Map<String, TestBean> tbm = new LinkedHashMap<>();
      tbm.put("testBean1", new TestBean("tb1"));
      tbm.put("testBean2", new TestBean("tb2"));
      return tbm;
    }

    public static Set<TestBean> testBeanSet() {
      Set<TestBean> tbs = new LinkedHashSet<>();
      tbs.add(new TestBean("tb1"));
      tbs.add(new TestBean("tb2"));
      return tbs;
    }
  }


  public static class CustomCollectionFactoryMethods {

    public static CustomMap<String, TestBean> testBeanMap() {
      CustomMap<String, TestBean> tbm = new CustomHashMap<>();
      tbm.put("testBean1", new TestBean("tb1"));
      tbm.put("testBean2", new TestBean("tb2"));
      return tbm;
    }

    public static CustomSet<TestBean> testBeanSet() {
      CustomSet<TestBean> tbs = new CustomHashSet<>();
      tbs.add(new TestBean("tb1"));
      tbs.add(new TestBean("tb2"));
      return tbs;
    }
  }


  public static class CustomMapConstructorInjectionBean {

    private CustomMap<String, TestBean> testBeanMap;

    @Autowired
    public CustomMapConstructorInjectionBean(CustomMap<String, TestBean> testBeanMap) {
      this.testBeanMap = testBeanMap;
    }

    public CustomMap<String, TestBean> getTestBeanMap() {
      return this.testBeanMap;
    }
  }


  public static class CustomSetConstructorInjectionBean {

    private CustomSet<TestBean> testBeanSet;

    @Autowired
    public CustomSetConstructorInjectionBean(CustomSet<TestBean> testBeanSet) {
      this.testBeanSet = testBeanSet;
    }

    public CustomSet<TestBean> getTestBeanSet() {
      return this.testBeanSet;
    }
  }


  public interface CustomMap<K, V> extends Map<K, V> {
  }


  @SuppressWarnings("serial")
  public static class CustomHashMap<K, V> extends LinkedHashMap<K, V> implements CustomMap<K, V> {
  }


  public interface CustomSet<E> extends Set<E> {
  }


  @SuppressWarnings("serial")
  public static class CustomHashSet<E> extends LinkedHashSet<E> implements CustomSet<E> {
  }


  public static class AnnotatedDefaultConstructorBean {

    @Autowired
    public AnnotatedDefaultConstructorBean() {
    }
  }


  public static class SelfInjectingFactoryBean implements FactoryBean<TestBean> {

    private final TestBean exposedTestBean = new TestBean();

    @Autowired
    TestBean testBean;

    @Override
    public TestBean getObject() {
      return exposedTestBean;
    }

    @Override
    public Class<?> getObjectType() {
      return TestBean.class;
    }

    @Override
    public boolean isSingleton() {
      return true;
    }

    public static SelfInjectingFactoryBean create() {
      return new SelfInjectingFactoryBean();
    }
  }


  public static class TestBeanFactory {

    @Order(1)
    public static TestBean newTestBean1() {
      return new TestBean();
    }

    @Order(0)
    public static TestBean newTestBean2() {
      return new TestBean();
    }
  }


}
