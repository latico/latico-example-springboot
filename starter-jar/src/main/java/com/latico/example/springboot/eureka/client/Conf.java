package com.latico.example.springboot.eureka.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-12-26 15:37
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "demo-yaml-config")
public class Conf {
}
