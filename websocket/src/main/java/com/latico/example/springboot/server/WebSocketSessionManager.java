package com.latico.example.springboot.server;

import javax.websocket.Session;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-08-30 18:35
 * @version: 1.0
 */
public interface WebSocketSessionManager {
    boolean addSessionToCache(Session session, String pathParam);

    boolean removeSessionFromCache(Session session, String pathParam);

    void sendMessageToAllSession(String message);

    boolean sendMessageByUid(String uid, String message);

    Session getSessionByUid(String uid);
}
