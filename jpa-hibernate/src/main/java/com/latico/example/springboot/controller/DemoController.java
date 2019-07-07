package com.latico.example.springboot.controller;

import com.latico.example.springboot.dao.entity.Demo;
import com.latico.example.springboot.dao.entity.Demo2;
import com.latico.example.springboot.dao.mapper.Demo2Repository;
import com.latico.example.springboot.dao.mapper.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    DemoRepository demoRepository;

    @Autowired
    Demo2Repository demo2Repository;

    @RequestMapping(value = "selectDemo")
    public List<Demo> selectDemo() {
        return demoRepository.findAll();
    }

    @RequestMapping(value = "selectDemo2")
    public List<Demo2> selectDemo2() {
        return demo2Repository.findAll();
    }
    @RequestMapping(value = "username/{username}")
    public Demo username(@PathVariable String username) {
        return demoRepository.username(username);
    }
    @RequestMapping(value = "findByUsername/{username}")
    public Demo findByUsername(@PathVariable String username) {
        return demoRepository.findByUsername(username);
    }

    @RequestMapping(value = "findById/{id}")
    public Demo findById(@PathVariable String id) {
        Optional<Demo> byId = demoRepository.findById(Integer.parseInt(id));
        return byId.get();
    }
}
