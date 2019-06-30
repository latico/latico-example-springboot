package com.latico.example.springboot.controller;

import com.latico.example.springboot.dao.primary.entity.Demo;
import com.latico.example.springboot.dao.primary.mapper.DemoRepository;
import com.latico.example.springboot.dao.secondary.entity.Demo2;
import com.latico.example.springboot.dao.secondary.mapper.Demo2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: LanDingDong
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


}
