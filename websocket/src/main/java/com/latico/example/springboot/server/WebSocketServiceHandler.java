package com.latico.example.springboot.server;

import javax.websocket.Session;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-08-30 18:49
 * @version: 1.0
 */
public interface WebSocketServiceHandler {
    void doBizByOnOpen(Session session, String pathParam);

    void doBizByOnClose(Session session, String pathParam);

    void doBizByOnMessage(Session session, String message, boolean last);

    void doBizByOnError(Session session, Throwable throwable);
}
