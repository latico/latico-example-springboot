package com.latico.example.springboot.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-09-02 15:41
 * @version: 1.0
 */
@Data
public class DemoBean implements Serializable {
    byte[] data;
    String str;
}
