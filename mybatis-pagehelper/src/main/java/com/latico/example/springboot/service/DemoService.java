package com.latico.example.springboot.service;

import com.latico.example.springboot.dao.entity.Demo;
import com.latico.example.springboot.dao.entity.Demo2;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-28 11:46
 * @Version: 1.0
 */
public interface DemoService {

    public List<Demo> selectDemo();

    public List<Demo2> selectDemo2();

    public List<Demo> findDemoByPage(int currentPage,int pageSize);
}
