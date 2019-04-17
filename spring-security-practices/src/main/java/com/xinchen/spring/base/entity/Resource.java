package com.xinchen.spring.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xinchen
 * @version 1.0
 * @date 17/04/2019 11:22
 */
@Entity
@Table(name = "app_resource")
@Data
@EqualsAndHashCode(callSuper=true)
public class Resource extends Base{

    @Column(name = "url")
    private String url;
}
