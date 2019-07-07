package com.latico.example.springboot.bean.dto;

import java.io.Serializable;

/**
 * <PRE>
 * 请求参数
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-20 15:56
 * @Version: 1.0
 */
public class RequestParam <T> implements Serializable {
    private static final long serialVersionUID = -8147655332473090271L;

    /**
     * 请求ID，决定请求的唯一性
     */
    private String id;
    /**
     * 请求的名称或者描述
     */
    private String name;

    /**
     * 扩展的参数
     */
    private T param;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestParam{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", param=").append(param);
        sb.append('}');
        return sb.toString();
    }
}
