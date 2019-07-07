package com.latico.example.springboot.listener;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <PRE>
 * 默认的启动监听器，如果有需要，可以多个，继承ApplicationListener即可
 * </PRE>
 * @Author: latico
 * @Date: 2019-05-09 18:21:03
 * @Version: 1.0
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 拿到任务
     */
    @Resource(name = "dataHandleJob")
    Job dataHandleJob;
    /**
     * 任务执行器
     */
    @Resource(name = "jobLauncher")
    JobLauncher jobLauncher;

    /**
     * 判断是否自动执行
     */
    @Value("${spring.batch.job.enabled}")
    private boolean batchEnabled;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

//        如果是自动启动了，那么就不在这里启动了
        if (!batchEnabled) {
            doService();
        }

    }

    /**
     * TODO
     * 启动业务处理
     */
    private void doService() {
        JobParameters jobParameters= new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            JobExecution result =jobLauncher.run(dataHandleJob, jobParameters);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}