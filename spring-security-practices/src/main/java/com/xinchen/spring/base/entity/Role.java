package com.xinchen.spring.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

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


    @ManyToMany(
            targetEntity = Resource.class,
            cascade = {CascadeType.REFRESH,CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "app_role_resource",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id",referencedColumnName = "id")}
    )
    private Collection<Resource> resources = new LinkedHashSet<>();
}
