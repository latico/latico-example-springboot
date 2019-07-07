package com.latico.example.springboot.dao.mapper.primary;

import com.latico.example.springboot.dao.entity.Demo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author latico
 * @since 2019-02-24
 */
public interface DemoMapper {
    List<Demo> findAll();
}
