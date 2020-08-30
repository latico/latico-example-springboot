package com.latico.example.springboot.config;

import com.latico.example.springboot.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <PRE>
 *  
 * </PRE>
 * @author: latico
 * @date: 2020-08-30 13:35:16
 * @version: 1.0
 */
@Configuration
public class WebSocketConfig {

    /**
     * 路径参数变量
     */
    public static final String pathParamName = "pathParam";


    /**
     * session的参数中，存放用户ID的参数的名称，这里默认使用路径参数的名称，此时要求连接的时候，用户要把uid作为路径的一部分
     */
    public static final String uidParamKeyName = "uid";


    /**
     * 连接websocket的访问相对路径，自定义，可以随意修改
     */
    public static final String websocketPath = "/websocket/{" + pathParamName + "}";

    public static final WebSocketSessionManager webSocketSessionManager = new WebSocketSessionManagerImpl(uidParamKeyName);
    public static final WebSocketServiceHandler webSocketServiceHandler = new WebSocketServiceHandlerImpl();


    /**
     * websocket服务器端点发布
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}