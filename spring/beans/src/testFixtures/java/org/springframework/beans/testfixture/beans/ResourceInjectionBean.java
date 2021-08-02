package org.springframework.beans.testfixture.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @date 2021-08-02 15:50
 */
public class ResourceInjectionBean extends AnnotatedInitDestroyBean {

    public boolean init2Called = false;

    public boolean init3Called = false;

    public boolean destroy2Called = false;

    public boolean destroy3Called = false;

    @Resource
    private TestBean testBean;

    private TestBean testBean2;

    @PostConstruct
    protected void init2() {
      if (this.testBean == null || this.testBean2 == null) {
        throw new IllegalStateException("Resources not injected");
      }
      if (!this.initCalled) {
        throw new IllegalStateException("Superclass init method not called yet");
      }
      if (this.init2Called) {
        throw new IllegalStateException("Already called");
      }
      this.init2Called = true;
    }

    @PostConstruct
    private void init() {
      if (this.init3Called) {
        throw new IllegalStateException("Already called");
      }
      this.init3Called = true;
    }

    @PreDestroy
    protected void destroy2() {
      if (this.destroyCalled) {
        throw new IllegalStateException("Superclass destroy called too soon");
      }
      if (this.destroy2Called) {
        throw new IllegalStateException("Already called");
      }
      this.destroy2Called = true;
    }

    @PreDestroy
    private void destroy() {
      if (this.destroyCalled) {
        throw new IllegalStateException("Superclass destroy called too soon");
      }
      if (this.destroy3Called) {
        throw new IllegalStateException("Already called");
      }
      this.destroy3Called = true;
    }

    @Resource
    public void setTestBean2(TestBean testBean2) {
      if (this.testBean2 != null) {
        throw new IllegalStateException("Already called");
      }
      this.testBean2 = testBean2;
    }

    public TestBean getTestBean() {
      return testBean;
    }

    public TestBean getTestBean2() {
      return testBean2;
    }
  }

