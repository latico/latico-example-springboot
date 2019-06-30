package com.latico.example.springboot.dao.primary.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <PRE>
 * 工作任务实体
 *
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-06-20 11:01:01
 * @Version: 1.0
 */
@Entity
@Table(name = "JOB_ENTITY")
@Data
@Accessors(chain = true)
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;          //job名称
    @Column(name = "job_group")
    private String jobGroup;      //job组名
    private String cron;          //执行的cron
    private String parameter;     //job的参数
    private String description;   //job描述信息
    @Column(name = "vm_param")
    private String vmParam;       //vm参数
    @Column(name = "jar_path")
    private String jarPath;       //job的jar路径
    private String status;        //job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job
}
