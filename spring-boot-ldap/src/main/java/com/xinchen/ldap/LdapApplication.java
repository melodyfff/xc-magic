package com.xinchen.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LDAP简称对应:
 *
 * o：organization（组织-公司）
 * ou：organization unit（组织单元-部门）
 * c：countryName（国家）
 * dc：domainComponent（域名）
 * sn：surname（姓氏）
 * cn：common name（常用名称）
 *
 */
@SpringBootApplication
public class LdapApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapApplication.class, args);
    }

}
