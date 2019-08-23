package com.xinchen.spring.service;

import com.xinchen.spring.annotation.Ok;
import org.springframework.stereotype.Service;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/08/2019 15:31
 */
@Service
public class AnnotationService {

    @Ok("hello")
    public void say(){
        System.out.println("Hello Annotation");
    }
}
