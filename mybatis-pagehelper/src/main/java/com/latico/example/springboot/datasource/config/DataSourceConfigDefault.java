package com.latico.example.springboot.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <PRE>
 * 默认数据源配置，也就是主数据源，需要使用@Primary注解
 * 指定扫描的mapper，sqlSession模板关联指定
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-02-26 09:24:06
 * @Version: 1.0
 */
@Configuration
@MapperScan(basePackages = DataSourceConfigDefault.MAPPER_PACKAGE, sqlSessionTemplateRef = "defaultSqlSessionTemplate")
public class DataSourceConfigDefault {
    /**
     * TODO
     * mapper的接口类
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    public static final String MAPPER_PACKAGE = "com.latico.example.springboot.dao.mapper.primary";
    /**
     * TODO
     * mapper的xml文件的位置
     */
    public static final String MAPPER_LOCATION = "classpath*:mapper/primary/**/*.xml";

    /**
     * TODO
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String DATASOURCE_CONFIG_PREFIX = "spring.datasource.primary";

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     *
     * @return
     * @ConfigurationProperties(prefix = "spring.datasource.primary") 配置数据源配置的前缀
     */
    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_CONFIG_PREFIX)
    @Primary
    public DataSource defaultDataSource() {
        //默认方式，springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        //druid方式
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @param dataSource @Qualifier("defaultDataSource")是{@link DataSourceConfigDefault#defaultDataSource()}
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //指定扫描的xml文件的位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

    /**
     * @param dataSource @Qualifier("defaultDataSource")是{@link DataSourceConfigDefault#defaultDataSource()}
     * @return
     */
    @Bean
    @Primary
    public DataSourceTransactionManager defaultTransactionManager(@Qualifier("defaultDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * @param sqlSessionFactory @Qualifier("defaultSqlSessionFactory")是{@link DataSourceConfigDefault#defaultSqlSessionFactory(DataSource)}
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionTemplate defaultSqlSessionTemplate(@Qualifier("defaultSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}