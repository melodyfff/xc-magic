package com.xinchen.spring.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/16 23:59
 */
@Entity
@Table(name = "app_role")
@Data
@EqualsAndHashCode(callSuper=true)
public class Role extends Base{
    @Column(name = "role_name",nullable = false)
    private String roleName;

}
