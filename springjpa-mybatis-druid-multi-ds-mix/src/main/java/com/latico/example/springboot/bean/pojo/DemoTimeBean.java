package com.latico.example.springboot.bean.pojo;

import java.util.Date;

/**
 * <PRE>
 * 
 * </PRE>
 * <B>项	       目：</B>研发中心公共-综合网管
 * <B>技术支持：</B>凯通科技股份有限公司 (c)
 * @version   <B>V1.0 2018年10月11日</B>
 * @author    <B><a href="mailto:landingdong@gdlaticosoft.com"> 蓝鼎栋 </a></B>
 * @since     <B>JDK1.6</B>
 */
public class DemoTimeBean {

    private Date time;
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
        final StringBuilder sb = new StringBuilder("DemoTimeBean{");
        sb.append("time=").append(time);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
