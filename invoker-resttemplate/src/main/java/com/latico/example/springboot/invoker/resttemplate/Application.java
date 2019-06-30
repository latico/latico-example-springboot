package com.latico.example.springboot.invoker.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <PRE>
 *  程序启动入口类
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-18 20:40:19
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.latico.example.springboot"})
//@MapperScan(basePackages = {"com.latico.archetype.springboot.dao.mapper"})
@ServletComponentScan
public class Application {


    /**
     * @param args
     */
    public static void main(String[] args) {
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
