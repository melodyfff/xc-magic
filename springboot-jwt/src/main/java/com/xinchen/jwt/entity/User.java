package com.xinchen.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/5/28 23:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;

    public boolean isEmpty() {
        return ((null == this.getId()) || (null == this.getUsername()) || (null == this.getPassword()));
    }
}
