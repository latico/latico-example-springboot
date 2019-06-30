package com.latico.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <PRE>
 * <p>
 * 程序启动入口类
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-06-19 21:27:59
 * @Version: 1.0
 */
@SpringBootApplication
public class Application {
    /**
     * springboot启动
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        //启动springboot容器
        SpringApplication.run(Application.class, args);
    }

}
