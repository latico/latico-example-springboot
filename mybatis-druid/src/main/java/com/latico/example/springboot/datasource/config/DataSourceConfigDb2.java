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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

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
@MapperScan(basePackages = DataSourceConfigDb2.MAPPER_PACKAGE, sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSourceConfigDb2 {
    /**
     * TODO
     * mapper的接口类
     * 精确到 master 目录，以便跟其他数据源隔离
     */
    public static final String MAPPER_PACKAGE = "com.latico.example.springboot.mybatisdruid.dao.mapper.secondary";
    /**
     * TODO
     * mapper的xml文件的位置
     */
    public static final String MAPPER_LOCATION = "classpath*:mapper/secondary/**/*.xml";

    /**
     * TODO
     * 数据源配置前缀，跟application配置文件的要对应上
     */
    public static final String DATASOURCE_CONFIG_PREFIX = "spring.datasource.secondary";

    /**
     * 在这里，用了什么数据源就返回什么数据源，如果是返回{@link DataSource}，那么springboot2.0会默认使用HikariCP作为数据源
     *
     * @return
     * @ConfigurationProperties(prefix = "spring.datasource.primary") 配置数据源配置的前缀
     */
    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_CONFIG_PREFIX)
    public DataSource db2DataSource() {
        //默认方式,springboot2.0会默认使用HikariCP作为数据源
//        return DataSourceBuilder.create().build();

        //druid方式
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //指定扫描的xml文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}