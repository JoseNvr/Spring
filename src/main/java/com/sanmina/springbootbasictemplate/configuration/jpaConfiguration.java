/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanmina.springbootbasictemplate.configuration;

//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import java.beans.PropertyVetoException;
//import java.util.HashMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author nestor_milian
 */
/*@Configuration
@EnableJpaRepositories(basePackages = "com.sanmina.springbootbasictemplate.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {

    @Value("${db.driver}")
    private String DB_DRIVER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_USERNAME;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.format_sql}")
    private String HIBERNATE_FORMAT_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Value("${hibernate.c3p0.max_size}")
    private String CONN_POOL_MAX_SIZE;

    @Value("${hibernate.c3p0.min_size}")
    private String CONN_POOL_MIN_SIZE;

    @Value("${hibernate.c3p0.idle_test_period}")
    private String CONN_POOL_IDLE_PERIOD;

    @Autowired
    private Environment env;

    /*@Primary
    @Bean
    public ComboPooledDataSource dataSource() {
        // a named datasource is best practice for later jmx monitoring
        ComboPooledDataSource dataSource = new ComboPooledDataSource("CienaRework");

        try {
            dataSource.setDriverClass(DB_DRIVER);
        } catch (PropertyVetoException pve) {
            System.out.println("Cannot load datasource driver (" + DB_DRIVER + ") : " + pve.getMessage());
            return null;
        }
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setMinPoolSize(Integer.parseInt(CONN_POOL_MIN_SIZE));
        dataSource.setMaxPoolSize(Integer.parseInt(CONN_POOL_MAX_SIZE));
        dataSource.setMaxIdleTime(Integer.parseInt(CONN_POOL_IDLE_PERIOD));

        return dataSource;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        properties.put("hibernate.dialect", HIBERNATE_DIALECT);
        properties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        properties.put("hibernate.format_sql", HIBERNATE_FORMAT_SQL);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }

}*/