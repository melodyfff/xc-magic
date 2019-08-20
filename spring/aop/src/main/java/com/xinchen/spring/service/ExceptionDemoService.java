package com.xinchen.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author xinchen
 * @version 1.0
 * @date 20/08/2019 16:25
 */
@Service
public class ExceptionDemoService {
    public void throwError(String name) throws Exception {
        throw new Exception(name);
    }
}
