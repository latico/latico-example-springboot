package com.latico.example.springboot.dao.secondary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * 多数据源时使用，可以直接拷贝该类配置N个数据源
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-02-26 09:25:32
 * @Version: 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary",
        basePackages = {DataSourceConfigSecondary.MAPPER_PACKAGE})
public class DataSourceConfigSecondary {
    /**
     * TODO
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String DATASOURCE_CONFIG_PREFIX = "spring.datasource.secondary";

    /**
     * TODO
     * mapper的接口类
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    public static final String MAPPER_PACKAGE = "com.latico.example.springboot.dao.secondary.mapper";

    /**
     * 实体目录
     */
    public static final String ENTITY_PREFIX = "com.latico.example.springboot.dao.secondary.entity";

    /**
     * 第二数据源
     */
    @Resource
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;
    /**
     * JAP配置
     */
    @Resource
    private JpaProperties jpaProperties;

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     *
     * @return
     * @ConfigurationProperties(prefix = "spring.datasource.primary") 配置数据源配置的前缀
     */
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = DATASOURCE_CONFIG_PREFIX)
    public DataSource secondaryDataSource() {
        //默认方式,springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        //druid方式
        return DruidDataSourceBuilder.create().build();
    }

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
    }

    // 注意
    @Bean(name = "entityManagerFactoryBuilderSecondary")
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderSecondary() {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, getVendorProperties(), persistenceUnitManager);
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource)
                .packages(ENTITY_PREFIX)
                .persistenceUnit("secondaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Bean(name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }


}