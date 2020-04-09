package com.xinchen.spring.module.base.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xinchen
 * @version 1.0
 * @date 12/10/2019 14:51
 */
@Entity
@Table(name = "app_resource")
@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends Base{
    @Column(name = "url")
    private String url;
}
