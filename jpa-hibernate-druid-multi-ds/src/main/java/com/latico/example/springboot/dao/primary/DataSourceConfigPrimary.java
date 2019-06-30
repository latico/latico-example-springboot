package com.latico.example.springboot.dao.primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * <PRE>
 * 默认数据源配置，也就是主数据源，需要使用@Primary注解
 * 指定扫描的mapper，sqlSession模板关联指定
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-02-26 09:24:06
 * @Version: 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {DataSourceConfigPrimary.MAPPER_PACKAGE})
public class DataSourceConfigPrimary {

    /**
     * TODO
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String DATASOURCE_CONFIG_PREFIX = "spring.datasource.primary";

    /**
     * TODO
     * mapper的接口类
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    public static final String MAPPER_PACKAGE = "com.latico.example.springboot.dao.primary.mapper";

    /**
     * 实体目录
     */
    public static final String ENTITY_PREFIX = "com.latico.example.springboot.dao.primary.entity";

    @Resource
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    /**
     * JPA配置
     */
    @Resource
    private JpaProperties jpaProperties;

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     *
     * @return
     * @ConfigurationProperties(prefix = "spring.datasource.primary") 配置数据源配置的前缀
     */
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = DATASOURCE_CONFIG_PREFIX)
    @Primary
    public DataSource primaryDataSource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        //druid方式
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }

    @Primary
    @Bean(name = "entityManagerFactoryBuilderPrimary")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderPrimary() {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, getVendorProperties(), persistenceUnitManager);
    }

    /**
     * 设置实体类所在位置
     */
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .packages(ENTITY_PREFIX)
                .persistenceUnit("primaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}