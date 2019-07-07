package com.latico.example.springboot.provider.eureka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-10 16:13
 * @Version: 1.0
 */
@RestController
public class DemoController {
    /**
     * 对象映射
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Value("${server.port}")
    String serverPort;
    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "hello")
    public String hello() {
        //返回字符串，需要包一层JSON
        return "端口" + serverPort + ":服务生产者数据:" + "你好";
    }

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    public String serverTimeStr() {
        //返回字符串，需要包一层JSON
        return objToJson("字符串类型:" + new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 测试URL中的键值对参数，可以多个 RequestParam
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam("name") String name) {
        //返回字符串，需要包一层JSON
        return objToJson("测试RequestParam:" + name);
    }

    /**
     * 测试路径中的参数 PathVariable
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testPathVariable/{name}")
    public String testPathVariable(@PathVariable String name) {
        //返回字符串，需要包一层JSON
        return objToJson("测试PathVariable:" + name);
    }

    /**
     * 测试路径中的多参数 多路径参数，PathVariable
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testMultiPathVariable/{name}/{value}")
    public String testMultiPathVariable(@PathVariable Map<String, String> map) {
        //返回字符串，需要包一层JSON
        return objToJson("测试testMultiPathVariable:" + map.get("name") + "/" + map.get("value"));
    }

    /**
     * 对象类型转json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String objToJson(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
