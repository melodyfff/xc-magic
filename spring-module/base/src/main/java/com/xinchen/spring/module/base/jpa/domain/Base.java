package com.xinchen.spring.module.base.jpa.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 *
 * {@link EntityListeners} AuditingEntityListener.class 为了能正常使用@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy
 * 同时还需要 {@link EnableJpaAuditing}
 *
 * @author xinchen
 * @version 1.0
 * @date 12/10/2019 14:47
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private boolean isNew = true;

    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
