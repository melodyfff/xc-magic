package com.xinchen.spring.base.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * 用户表
 *
 * CascadeType 参考：https://www.jianshu.com/p/e8caafce5445
 *
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 22:09
 */
@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(callSuper=true)
public class User extends Base{
    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(name = "app_user_role",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private Collection<Role> roles = new ArrayList<>();
}
