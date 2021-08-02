package org.springframework.beans.testfixture.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @date 2021-08-02 16:42
 */
class AnnotatedInitDestroyBean {
  public boolean initCalled = false;

  public boolean destroyCalled = false;

  @PostConstruct
  private void init() {
    if (this.initCalled) {
      throw new IllegalStateException("Already called");
    }
    this.initCalled = true;
  }

  @PreDestroy
  private void destroy() {
    if (this.destroyCalled) {
      throw new IllegalStateException("Already called");
    }
    this.destroyCalled = true;
  }
}
