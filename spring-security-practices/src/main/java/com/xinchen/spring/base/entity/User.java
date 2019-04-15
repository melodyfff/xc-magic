package com.xinchen.spring.base.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 22:09
 */
@Entity
@Table(name = "app_user")
@Data
public class User extends Base{
    @Column(name = "user_name",nullable = false)
    private String userName;
}
