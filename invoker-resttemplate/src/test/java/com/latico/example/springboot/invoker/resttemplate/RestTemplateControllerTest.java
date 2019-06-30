package com.latico.example.springboot.invoker.resttemplate;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class RestTemplateControllerTest {

    @Test
    public void hello() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:9000/hello", String.class);
        String str = "RestTemplateRibbon服务调用者结果:" + result;

        System.out.println(str);
    }
}