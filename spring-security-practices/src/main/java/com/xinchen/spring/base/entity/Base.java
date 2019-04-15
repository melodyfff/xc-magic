package com.xinchen.spring.base.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 22:26
 */
@Data
@MappedSuperclass
public class Base implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
}


