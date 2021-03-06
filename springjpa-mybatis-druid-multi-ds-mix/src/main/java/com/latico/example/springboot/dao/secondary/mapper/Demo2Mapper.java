package com.latico.example.springboot.dao.secondary.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.latico.example.springboot.dao.secondary.entity.Demo2;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author latico
 * @since 2019-02-26
 */
public interface Demo2Mapper extends BaseMapper<Demo2> {
    /**
     * @return
     */
    List<Demo2> findAll();
}
