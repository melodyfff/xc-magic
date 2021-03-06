package com.xinchen.base.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/3 19:48
 */
@Data
@Entity
@Table(name = "xc_user")
//@Proxy(lazy = false)
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private long id;

    @Column(name = "name")
    @JsonProperty("user_name")
    private String name;
}
