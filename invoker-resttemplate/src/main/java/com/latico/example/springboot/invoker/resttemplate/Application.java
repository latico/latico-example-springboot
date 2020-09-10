package com.latico.example.springboot.invoker.resttemplate;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <PRE>
 *  程序启动入口类
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-18 20:40:19
 * @Version: 1.0
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.latico.archetype.springboot.dao.mapper"})
@ServletComponentScan
public class Application {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOG.info("启动完成");
    }

}
