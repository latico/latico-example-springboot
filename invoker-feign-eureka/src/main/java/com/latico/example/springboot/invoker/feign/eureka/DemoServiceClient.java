package com.latico.example.springboot.invoker.feign.eureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-05 16:13
 * @Version: 1.0
 */
@FeignClient("provider-eureka") //声明调用的服务名称，自带负载均衡
public interface DemoServiceClient {

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "hello")
    public String hello();

    /**
     * @return 字符串类型数据
     */
    @RequestMapping(value = "serverTimeStr")
    public String serverTimeStr();

    /**
     * 测试URL中的键值对参数，可以多个 RequestParam
     *
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam("name") String name);

    /**
     * 测试路径中的参数 PathVariable
     *
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testPathVariable/{name}")
    public String testPathVariable(@PathVariable(value = "name") String name);

    /**
     * 测试路径中的多参数 多路径参数，PathVariable
     *
     * @return 字符串类型数据
     */
    @RequestMapping(value = "testMultiPathVariable/{name}/{value}")
    public String testMultiPathVariable(@PathVariable(value = "name") String name, @PathVariable(value = "value") String value);

}
