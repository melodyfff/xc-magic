package com.xinchen.spring.beans.annotation;


/**
 * @date 2021-08-06 17:12
 */
@Api("nestedTestBean")
public class NestedTestBean {
private final TestBean testBean;

  public NestedTestBean(TestBean testBean) {
    this.testBean = testBean;
  }

  public TestBean getCs() {
    return testBean;
  }
}
