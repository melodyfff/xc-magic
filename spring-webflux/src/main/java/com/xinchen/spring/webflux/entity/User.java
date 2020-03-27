package com.xinchen.spring.webflux.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/27 23:38
 */
@Data
public class User implements Serializable {
    private String name;

    public User(String name) {
        this.name = name;
    }
}
