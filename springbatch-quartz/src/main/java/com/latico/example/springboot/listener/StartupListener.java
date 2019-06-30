package com.latico.example.springboot.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * <PRE>
 * 默认的启动监听器，如果有需要，可以多个，继承ApplicationListener即可
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-05-09 18:21:03
 * @Version: 1.0
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        doService();
    }

    /**
     * TODO
     * 启动业务处理
     */
    private void doService() {
    }


}