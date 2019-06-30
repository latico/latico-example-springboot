package com.latico.example.springboot.dao.secondary.mapper;

import com.latico.example.springboot.common.PageHelperUtils;
import com.latico.example.springboot.dao.DaoTestApplication;
import com.latico.example.springboot.dao.secondary.entity.Demo2;
import com.github.pagehelper.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DaoTestApplication.class)
public class Demo2MapperTest {
    @Autowired
    Demo2Mapper demo2Mapper;

    @Test
    public void select() {

        Page<Demo2> page = PageHelperUtils.startPage(1, 3);
        System.out.println("page查询前:" + page);
        List<Demo2> datas = demo2Mapper.findAll();
        System.out.println("page查询前:" + page);
        System.out.println("数据结果:" + datas);
    }
}