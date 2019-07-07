package com.latico.example.springboot.controller;

import com.latico.example.springboot.dao.primary.entity.Demo;
import com.latico.example.springboot.dao.primary.mapper.DemoRepository;
import com.latico.example.springboot.dao.secondary.entity.Demo2;
import com.latico.example.springboot.dao.secondary.mapper.Demo2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <PRE>
 * 测试
 * 数据库查询接口等
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-10 16:13
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "test")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TestController {

    /**
     * 测试JPA方式
     */
    @Autowired
    DemoRepository demoRepository;

    /**
     * 测试Mybatis方式
     */
    @Autowired
    Demo2Mapper demo2Mapper;

    /**
     * @return 查询数据库所有Demo数据
     */
    @RequestMapping(value = "selectDemo")
    public List<Demo> selectDemo() {
        return demoRepository.findAll();
    }

    @RequestMapping(value = "selectDemo2")
    public List<Demo2> selectDemo2() {
        return demo2Mapper.findAll();
    }


}
