package com.self.sell.modules.seller.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class SellerWebSocket {

    private Session session;


    private static CopyOnWriteArrayList<SellerWebSocket> webSockets = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【 WebSocket 消息 】 有新的连接数,总数：{}", webSockets.size());
    }


    @OnClose
    public void onClose() {
        webSockets.remove(this);
        log.info("【websocket 消息 】 连接断开，总数 :{}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【 webSocket 消息】，收到客户端发送来的消息，message = {}", message);
    }

    public void sendMessage(String message) {
        for (SellerWebSocket webSocket : webSockets) {
            log.info("【 webSocket 消息】 广播发送下消息, message ={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.info(e.getMessage(), e);
            }

        }

    }


}
