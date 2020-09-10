package com.latico.example.springboot.server;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.example.springboot.config.WebSocketConfig;

import javax.websocket.Session;
import java.util.List;
import java.util.Map;

/**
 * <PRE>
 * 处理websocket的业务
 * </PRE>
 *
 * @author: latico
 * @date: 2020-08-30 17:30
 * @version: 1.0
 */
public class WebSocketServiceHandlerImpl implements WebSocketServiceHandler {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServiceHandlerImpl.class);

    private final WebSocketSessionManager webSocketSessionManager = WebSocketConfig.webSocketSessionManager;
    /**
     * 建立连接成功调用
     * @param session
     * @param pathParam
     */
    @Override
    public void doBizByOnOpen(Session session, String pathParam) {
        LOG.info("新用户连接session身份:{}", session.getUserPrincipal());
        //打印一些信息
        Map<String, String> pathParameters = session.getPathParameters();
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        LOG.info("sessionId={} pathParam={} \r\ngetPathParameters:{} \r\nrequestParameterMap:{}", session.getId(), pathParam, pathParameters, requestParameterMap);
        webSocketSessionManager.sendMessageToAllSession("欢迎：sessionId=" + session.getId()+ " pathParam=" +pathParam + "加入连接！");
    }
    @Override
    public void doBizByOnClose(Session session, String pathParam) {
    }
    @Override
    public void doBizByOnMessage(Session session, String message, boolean last) {
        LOG.info("服务端收到消息, sessionId=[{}] last=[{}] 内容=[{}]", session.getId(), last, message);
    }
    @Override
    public void doBizByOnError(Session session, Throwable throwable) {
        LOG.error("发生错误 sessionId=[{}]", throwable, session.getId());
    }
}
