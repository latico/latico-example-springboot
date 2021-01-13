package com.latico.example.springboot.invoker.feign.eureka;

import com.latico.commons.common.util.other.SnowflakeIdUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    /**
     *
     */
    @Test
    public void test(){
        System.out.println(Long.MAX_VALUE);
        System.out.println(SnowflakeIdUtils.generateId());
        System.out.println(System.currentTimeMillis());
    }
}