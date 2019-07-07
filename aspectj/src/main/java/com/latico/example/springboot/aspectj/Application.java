package com.latico.example.springboot.aspectj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <PRE>
 *  程序启动入口类
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-13 22:04:55
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages={"com.latico.example.springboot.aspectj"})
@ServletComponentScan
public class Application {
    /**
     * springboot启动
     * @param args
     */
    public static void main(String[] args) {
        //启动springboot容器
        SpringApplication.run(Application.class, args);

    }
}
