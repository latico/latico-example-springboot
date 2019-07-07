package com.latico.example.springboot.service.impl;

import com.latico.example.springboot.dao.entity.Demo;
import com.latico.example.springboot.dao.entity.Demo2;
import com.latico.example.springboot.dao.mapper.secondary.Demo2Mapper;
import com.latico.example.springboot.dao.mapper.primary.DemoMapper;
import com.latico.example.springboot.service.DemoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-28 11:50
 * @Version: 1.0
 */
@SuppressWarnings("ALL")
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    DemoMapper demoMapper;

    @Autowired
    Demo2Mapper demo2Mapper;

    @Override
    public List<Demo> selectDemo() {
        return demoMapper.findAll();
    }

    @Override
    public List<Demo2> selectDemo2() {
        return demo2Mapper.findAll();
    }

    @Override
    public List<Demo> findDemoByPage(int currentPage, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        //全部商品，分页查询结果
        List<Demo> allItems = demoMapper.findAll();

        return allItems;
    }


}
