package com.latico.example.springboot.invoker.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("http://localhost:9000/hello", String.class);
        return "RestTemplateRibbon服务调用者结果:" + result;
    }
}