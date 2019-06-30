package com.latico.example.springboot.invoker.feign.eureka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class DemoServiceClientController {
    /**
     * 对象映射
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DemoServiceClient demoServiceClient;

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "hello")
    public String hello() {
        //返回字符串，需要包一层JSON
        return objToJson("Feign字符串类型:" + demoServiceClient.hello());
    }

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    public String serverTimeStr() {
        //返回字符串，需要包一层JSON
        return objToJson("Feign字符串类型:" + demoServiceClient.serverTimeStr());
    }

    /**
     * 测试URL中的键值对参数，可以多个 RequestParam
     * 浏览器方式测试：http://localhost:9080/client/testRequestParam?name=nihao
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam("name") String name) {
        //返回字符串，需要包一层JSON
        return objToJson("Feign测试RequestParam:" + demoServiceClient.testRequestParam(name));
    }

    /**
     * 测试路径中的参数 PathVariable
     * 浏览器方式测试：http://localhost:9080/client/testPathVariable/nihao
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testPathVariable/{name}")
    public String testPathVariable(@PathVariable(value = "name") String name) {
        //返回字符串，需要包一层JSON
        return objToJson("Feign测试PathVariable:" + demoServiceClient.testPathVariable(name));
    }

    /**
     * 测试路径中的多参数 多路径参数，PathVariable
     * 浏览器方式测试：http://localhost:9080/client/testMultiPathVariable/nihao/dajiahao
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testMultiPathVariable/{name}/{value}")
    public String testMultiPathVariable(@PathVariable(value = "name") String name, @PathVariable(value = "value") String value) {
        //返回字符串，需要包一层JSON
        return objToJson("Feign测试testMultiPathVariable:" + demoServiceClient.testMultiPathVariable(name, value));
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