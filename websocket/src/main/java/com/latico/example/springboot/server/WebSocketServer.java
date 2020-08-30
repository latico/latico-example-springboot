package com.latico.example.springboot.server;

import com.latico.example.springboot.config.WebSocketConfig;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * <PRE>
 *  websocket的服务端
 *  注意：使用了@ServerEndpoint注解的类，不能使用spring的autowired注解
 * 目前默认示例配置了pathParam，但是实际使用不建议使用pathParam，pathParam建议是存放用户ID，这样可以决定唯一值
 * 而应该使用 Session里面存储URL的参数，比如："ws:localhost:8080/websocket/chat?username=xiaoming";
 *
 Map<String, String> pathParameters = session.getPathParameters();
 Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
 LOG.info("session.getPathParameters():" + pathParameters.toString());
 LOG.info("session.getRequestParameterMap():" + requestParameterMap.toString());

 * </PRE>
 *
 * @author: latico
 * @date: 2020-08-30 13:35:16
 * @version: 1.0
 */
@Component
@ServerEndpoint(WebSocketConfig.websocketPath)
public class WebSocketServer {

    /**
     * 路径参数变量
     */
    private static final String pathParamName = WebSocketConfig.pathParamName;
    /**
     * session管理实现
     */
    private final WebSocketSessionManager webSocketSessionManager = WebSocketConfig.webSocketSessionManager;
    /**
     * 业务处理器实现
     */
    private final WebSocketServiceHandler webSocketServiceHandler = WebSocketConfig.webSocketServiceHandler;

    /**
     * 建立连接成功调用
     * @param session
     * @param pathParam
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = pathParamName) String pathParam) {
        boolean status = webSocketSessionManager.addSessionToCache(session, pathParam);
        if (status) {
            webSocketServiceHandler.doBizByOnOpen(session, pathParam);
        }
    }

    /**
     * 关闭连接时调用
     * 先触发了OnError的情况下，也会触发该方法，这个方法是只要session关闭，都会触发
     * @param pathParam
     */
    @OnClose
    public void onClose(Session session, @PathParam(value = pathParamName) String pathParam) {
        boolean status = webSocketSessionManager.removeSessionFromCache(session, pathParam);
        if (status) {
            webSocketServiceHandler.doBizByOnClose(session, pathParam);
        }
    }

    /**
     * 收到客户端信息 触发
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(Session session, String message, boolean last) {
        webSocketServiceHandler.doBizByOnMessage(session, message, last);
    }

    /**
     * 错误时调用
     * session发生了严重错误，比如使用非正常方式退出
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        webSocketServiceHandler.doBizByOnError(session, throwable);

    }

}
