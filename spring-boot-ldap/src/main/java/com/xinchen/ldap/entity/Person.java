package com.xinchen.ldap.entity;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

/**
 * @author xinchen
 * @version 1.0
 * @date 28/06/2019 14:15
 */
@Data
@Entry(base = "ou=People,dc=springframework,dc=org", objectClasses = "inetOrgPerson")
public class Person {
    @Id
    private Name dn;

    @DnAttribute(value="uid",index = 3)
    private String uid;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String surname;

}
