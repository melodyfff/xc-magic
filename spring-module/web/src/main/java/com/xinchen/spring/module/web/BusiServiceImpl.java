package com.xinchen.spring.module.web;

import com.xinchen.spring.module.web.core.service.BusiService;

/**
 *
 * core中的业务具体处理
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/10 0:26
 */
public class BusiServiceImpl implements BusiService<String,String> {

    @Override
    public String applay(String s) {
        return s;
    }
}
