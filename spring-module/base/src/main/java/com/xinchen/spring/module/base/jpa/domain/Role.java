package com.xinchen.spring.module.base.jpa.domain;

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
import java.util.List;

/**
 * @author xinchen
 * @version 1.0
 * @date 12/10/2019 14:51
 */
@Entity
@Table(name = "app_role")
@Data
@EqualsAndHashCode(callSuper=true)
public class Role extends Base{

    @Column(name = "role_name",nullable = false)
    private String roleName;

//    @OneToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User user;

    @ManyToMany(
            targetEntity = Resource.class,
            cascade =  {CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "app_role_resource",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id",referencedColumnName = "id")}
    )
    private List<Resource> resources = new ArrayList<>();
}
