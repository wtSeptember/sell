package com.gzcstec.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocketService {

    private Session session;

    private static CopyOnWriteArraySet<WebSocketService> webSockets;

    static {
        webSockets = new CopyOnWriteArraySet<>();
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【websock消息】，有新的连接，连接总数={}" , webSockets.size());
    }

    @OnClose
    public void onClose(Session session) {
        webSockets.remove(this);
        log.info("【websock消息】，有连接断开，连接总数={}" , webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websock消息】，收到新的消息，msg={}" , message);
    }

    public void sendMessage(String message) {
        for(WebSocketService webSocket : webSockets) {
            log.info("【websock消息】广播消息");
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【websock消息】，广播消息失败，e={}" , e);
            }
        }
    }
}
