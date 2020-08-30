package com.latico.example.springboot.server;

import com.latico.commons.common.util.logging.Logger;
import com.latico.commons.common.util.logging.LoggerFactory;
import com.latico.commons.common.util.string.StringUtils;
import com.latico.example.springboot.config.WebSocketConfig;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <PRE>
 * Session管理器
 * </PRE>
 *
 * @author: latico
 * @date: 2020-08-30 16:39
 * @version: 1.0
 */
public class WebSocketSessionManagerImpl implements WebSocketSessionManager {
    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketSessionManagerImpl.class);

    /**
     * session的参数中，存放用户ID的参数的名称，这里默认使用路径参数的名称，此时要求连接的时候，用户要把uid作为路径的一部分
     */
    protected final String uidParamKeyName = WebSocketConfig.uidParamKeyName;

    /**
     *
     */
    private final ConcurrentHashMap<String, Session> sessionIdSessionCache = new ConcurrentHashMap<>();

    /**
     *
     */
    private final ConcurrentHashMap<String, Session> uidSessionMap = new ConcurrentHashMap<>();

    /**
     * 增加session到缓存
     * @param session
     * @param pathParam
     */
    @Override
    public synchronized boolean addSessionToCache(Session session, String pathParam) {
        if (session == null) {
            return false;
        }
        String sessionId = session.getId();

        final String uid = getUid(session);
        if (StringUtils.isNotBlank(uid)) {
            Session oldSession = uidSessionMap.get(uid);
            if (oldSession != null) {
                String reasonPhrase = "用户申请新连接，旧连接被关闭";
                String oldSessionId = oldSession.getId();
                LOG.warn("{}:sessionId=[{}] pathParam=[{}] uid=[{}]", reasonPhrase, oldSessionId, pathParam, uid);
                CloseReason.CloseCodes closeCode = CloseReason.CloseCodes.GOING_AWAY;

                //session被关闭了会触发OnClose方法，而OnClose方法里面会调用移出缓存操作，所以这里不需要手工调用移出缓存操作
                closeSession(oldSession, pathParam, closeCode, reasonPhrase, uid);
            }
        }

        sessionIdSessionCache.put(sessionId, session);

        if (StringUtils.isNotBlank(uid)) {
            uidSessionMap.put(uid, session);
        }

        LOG.info("新连接加入websocket, sessionId=[{}] pathParam=[{}] uid=[{}] 当前总连接数=[{}]", sessionId, pathParam, uid, sessionIdSessionCache.size());
        return true;
    }

    /**
     * 通过session拿到用户ID
     * @param session
     * @return
     */
    private String getUid(Session session) {
        Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
        List<String> values = requestParameterMap.get(uidParamKeyName);
        if (values != null && !values.isEmpty()) {
            return values.get(0);
        }
        return null;
    }

    /**
     * 删除缓存中的数据
     * @param session
     * @param pathParam
     */
    @Override
    public synchronized boolean removeSessionFromCache(Session session, String pathParam) {
        if (session == null) {
            return false;
        }
        String sessionId = session.getId();
        sessionIdSessionCache.remove(sessionId);

        String uid = getUid(session);
        if (StringUtils.isNotBlank(uid)) {
            uidSessionMap.remove(uid);
        }

        LOG.info("Session连接移出缓存, sessionId=[{}] pathParam=[{}] uid=[{}] 当前总连接数=[{}]", sessionId, pathParam, uid, sessionIdSessionCache.size());
        return true;
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    @Override
    public void sendMessageToAllSession(String message){
        Collection<Session> values = sessionIdSessionCache.values();
        if (values != null) {
            for (Session session : values) {
                String uid = getUid(session);
                sendMessage(session, uid, message);
            }
        }
    }

    /**
     * 发送消息
     *
     * @param session
     * @param uid
     * @param message
     * @return
     */
    public static boolean sendMessage(Session session, String uid, String message) {
        if (session == null) {
            return false;
        }
        boolean status = false;
        synchronized (session) {
            try {
                session.getBasicRemote().sendText(message);
                status = true;
            } catch (Exception e) {
                LOG.error("", e);
            }
        }

        LOG.info("发送消息给sessionId=[{}] uid=[{}] 状态:{} 消息:{}", session.getId(), uid, status, message);
        return status;
    }

    /**
     * 关闭一个session
     * @param session
     * @param pathParam
     * @param closeCode 关闭状态码
     * @param reasonPhrase 详细原因
     * @param uid
     */
    public static void closeSession(Session session, String pathParam, CloseReason.CloseCodes closeCode, String reasonPhrase, String uid) {
        try {
            session.close(new CloseReason(closeCode, reasonPhrase));
        } catch (Exception e) {
            LOG.error(e);
        }
        LOG.info("关闭sessionId=[{}] pathParam=[{}] uid=[{}]", session.getId(), pathParam, uid);
    }

    /**
     * 发送消息
     * @param uid
     * @param message
     * @return
     */
    @Override
    public boolean sendMessageByUid(String uid, String message) {
        if (uid != null) {
            Session session = uidSessionMap.get(uid);
            if (session != null) {
                return sendMessage(session, uid, message);
            }
        }
        return false;
    }

    @Override
    public Session getSessionByUid(String uid) {
        if (uid != null) {
            return uidSessionMap.get(uid);
        }
        return null;
    }

}
