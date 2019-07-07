package com.latico.example.springboot.transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * <PRE>
 *  使用程序的方式注入数据库连接，也可以使用配置文件的方式，这里为了简便
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-27 10:21:41
 * @Version: 1.0
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //1.初始化
        SpringApplication application=  new SpringApplication(Application.class);

        //2.添加数据源
        Map<String,Object> map = new HashMap<>();
        map.put("spring.datasource.url","jdbc:mysql://localhost:3306/test?useSSL=false");
        map.put("spring.datasource.username","root");
        map.put("spring.datasource.password","root");

        //3.开启驼峰映射 (Such as account_id ==> accountId)
        map.put("mybatis.configuration.map-underscore-to-camel-case",true);
        application.setDefaultProperties(map);

        //4.启动应用
        application.run(args);

        System.out.println("启动完成");
    }
}