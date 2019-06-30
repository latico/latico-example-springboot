package com.latico.example.springboot.invoker.resttemplate.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
    /**
     * 定义为私有可能会报错
     */
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        //输入的URL是服务名称
        String result = restTemplate.getForObject("http://provider-eureka/hello", String.class);
        return "RestTemplateRibbon服务调用者结果:" + result;
    }
}