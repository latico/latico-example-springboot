package com.latico.example.springboot.invoker.feign.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <PRE>
 *  程序启动入口类
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-13 22:04:55
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackageClasses={Application.class})
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients
public class Application {
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        //启动springboot容器
        SpringApplication.run(Application.class, args);

        System.out.println("启动完成");
    }
}
