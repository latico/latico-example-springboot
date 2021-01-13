package com.latico.example.springboot.provider.eureka;

import com.latico.commons.common.util.collections.CollectionUtils;
import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.system.SystemUtils;
import com.latico.commons.spring.util.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.net.InetAddress;
import java.util.List;

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
public class Application {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    /**
     * springboot启动
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        //启动springboot容器
        SpringApplication.run(Application.class, args);

        LOG.info("启动完成");
        printHomeUrl();

    }

    /**
     * 打印主页URL
     */
    private static void printHomeUrl() {
        String ip = "localhost";
        List<InetAddress> allPhysicsInetAddress = SystemUtils.getAllPhysicsInetAddress();
        if (CollectionUtils.isNotEmpty(allPhysicsInetAddress)) {
            InetAddress inetAddress = allPhysicsInetAddress.get(0);
            ip = inetAddress.getHostAddress();
        }
        String serverPort = SpringUtils.getApplicationConfigByKey("server.port");
        LOG.info("主页URL: http://{}:{}", ip, serverPort);
    }
}
