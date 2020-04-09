package com.xinchen.spring.module.base.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * EnableJpaRepositories :       启用JPA存储库的注释
 * EnableTransactionManagement : 启用Spring的注释驱动的事务管理功能
 *
 * @author xinchen
 * @version 1.0
 * @date 12/10/2019 16:08
 */
@EnableJpaRepositories(
        basePackages = "com.xinchen.spring.module.base.jpa.repositories",
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager"

)
@EnableTransactionManagement
@EnableJpaAuditing
public class JpaConfiguration {


    @Bean
    public DataSource dataSource(){
        // 使用内存数据库
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(DataSource dataSource){

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabase(Database.H2);


        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.xinchen.spring.module.base.jpa.domain");
        factory.setDataSource(dataSource);

        // 指定的持久性单元名称作为默认名称
        factory.setPersistenceUnitName("app");

        // 配置JPA属性；如Hibernate中指定是否显示SQL的是否显示、方言等
        Properties hibernateConfig = new Properties();
        hibernateConfig.setProperty("hibernate.show_sql", "true");
        hibernateConfig.setProperty("hibernate.format_sql", "true");
        hibernateConfig.setProperty("hibernate.hbm2ddl.auto", "update");
        factory.setJpaProperties(hibernateConfig);

        return factory;
    }

    @Bean
    public PlatformTransactionManager appTransactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
