package com.latico.example.springboot.dao.mapper.secondary;


import com.latico.example.springboot.dao.entity.Demo2;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author latico
 * @since 2019-02-26
 */
public interface Demo2Mapper {
    List<Demo2> findAll();
}
