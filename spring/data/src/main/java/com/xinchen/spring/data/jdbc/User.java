package com.xinchen.spring.data.jdbc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/22 22:15
 */
@Getter
@Setter
@ToString
public class User {
    private long id;

    private String userName;

    private String password;
}
