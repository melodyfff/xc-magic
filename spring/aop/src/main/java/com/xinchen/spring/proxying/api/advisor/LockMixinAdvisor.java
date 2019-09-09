package com.xinchen.spring.proxying.api.advisor;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 *
 * LockMixin顾问对象
 *
 * 指定advice和需要建议的类型(class)
 *
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 11:19
 */
public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

    public LockMixinAdvisor() {
        // Create a DefaultIntroductionAdvisor for the given advice.
        // @param advice the Advice to apply
        // @param intf the interface to introduce
        super(new LockMixin(), Lockable.class);
    }
}
