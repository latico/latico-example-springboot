package com.latico.example.springboot.bean.dto;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-20 16:09
 * @Version: 1.0
 */
public class ResponseParam <T> implements Serializable {
    private static final long serialVersionUID = 7334793210830301012L;
    /**
     * 请求ID，决定请求的唯一性
     */
    private String id;
    /**
     * 请求的名称或者描述
     */
    private String name;
    /**
     * 描述
     */
    private String desc;

    /**
     * 总状态，成功/失败，true/false
     */
    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseParam{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", status=").append(status);
        sb.append(", param=").append(param);
        sb.append('}');
        return sb.toString();
    }
}
