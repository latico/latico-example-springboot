package com.latico.example.springboot.eureka.client;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <PRE>
 *  web方式启动的时候的初始化，为了让tomcat等web服务器方式启动，能检测到启动类
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-08 17:25:46
 * @Version: 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
