package com.xinchen.service.async;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/6 20:09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
