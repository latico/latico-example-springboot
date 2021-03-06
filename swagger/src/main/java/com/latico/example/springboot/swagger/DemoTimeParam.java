package com.latico.example.springboot.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <PRE>
 * 时间对象
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-06-25 14:25:20
 * @Version: 1.0
 */
@ApiModel("演示时间bean")
public class DemoTimeParam implements Serializable {

    private static final long serialVersionUID = -4817079619875990184L;

    @ApiModelProperty("时间")
    private Date time;

    @ApiModelProperty(value = "名称", example = "时间1")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }


    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DemoTimeParam{");
        sb.append("time=").append(time);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
