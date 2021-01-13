package com.latico.example.springboot;

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
@SpringBootApplication(scanBasePackageClasses={Application.class})
//druid的界面功能需要这个主机
@ServletComponentScan
public class Application {
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        //启动springboot容器
        SpringApplication.run(Application.class, args);

        //开始处理业务流程
        startProcessService();
    }
    private static void startProcessService() {
        System.out.println("开始处理业务");
        int i = 0;

        while (i++ <= 5) {
            System.out.println("业务正在处理");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("处理业务完成");
    }
}
